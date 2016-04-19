package vm.merkurev.music;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vm.merkurev.music.model.Singer;

/**
 * A fragment representing a single Singer detail screen.
 * This fragment is either contained in a {@link SingerListActivity}
 * in two-pane mode (on tablets) or a {@link SingerDetailActivity}
 * on handsets.
 * <p/>
 * I decided not to use presenter here, because this fragment is quite simple, and it will be
 * pretty much hard to use animation in presenter
 */
public class SingerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Singer mItem;

    private ImageView bigCover;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SingerDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = (Singer) getArguments().getSerializable(ARG_ITEM_ID);
        }

        //new transition animation
        if (Build.VERSION.SDK_INT >= 21) {
            FragmentActivity activityCompat = getActivity();
            if (activityCompat.getWindow().getSharedElementEnterTransition() != null) {
                activityCompat.getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        //onransition end load big picture, before use small one
                        if (bigCover != null) {
                            Picasso.with(getActivity())
                                    .load(mItem.getCover().getBig())
                                    .noFade()
                                    .noPlaceholder()
                                    .into(bigCover);
                        }

                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setting view fields
        View rootView = inflater.inflate(R.layout.fragment_singer_detail, container, false);
        if (mItem != null) {
            TextView desc = (TextView) rootView.findViewById(R.id.description);
            if (desc != null) {
                desc.setText(mItem.getDescription());
            }

            bigCover = (ImageView) rootView.findViewById(R.id.coverBig);
            //load big picture from very beginning if animation is not implemented(SDK<21)
            if (bigCover != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    Picasso.with(getActivity()).load(mItem.getCover().getSmall()).into(bigCover);
                } else {
                    Picasso.with(getActivity()).load(mItem.getCover().getBig()).into(bigCover);
                }
            }

            //string plurals
            String albumsString = getActivity().getResources().getQuantityString(R.plurals.albums,
                    mItem.getAlbums(), mItem.getAlbums());
            String tracksString = getActivity().getResources().getQuantityString(R.plurals.tracks,
                    mItem.getTracks(), mItem.getTracks());
            TextView amounts = (TextView) rootView.findViewById(R.id.amount_details);
            if (amounts != null) {
                amounts.setText(albumsString + ", " + tracksString);
            }

            TextView genres = (TextView) rootView.findViewById(R.id.genres_details);
            if (genres != null) {
                genres.setText(getGenres(mItem.getGenres()));
            }

        }

        return rootView;
    }

    private String getGenres(List<String> genres) {
        if (genres == null || genres.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(genres.get(0));
        for (int i = 1; i < genres.size(); i++) {
            builder.append(", ");
            builder.append(genres.get(i));
        }
        return builder.toString();
    }
}

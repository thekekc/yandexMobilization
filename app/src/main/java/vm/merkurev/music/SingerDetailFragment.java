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
 */
public class SingerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
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
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = (Singer) getArguments().getSerializable(ARG_ITEM_ID);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            FragmentActivity activityCompat = getActivity();
            if (activityCompat.getWindow().getSharedElementEnterTransition()!=null) {
                activityCompat.getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
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
        View rootView = inflater.inflate(R.layout.fragment_singer_detail, container, false);
        if (mItem != null) {
            TextView desc = (TextView) rootView.findViewById(R.id.description);
            if (desc != null) {
                desc.setText(mItem.getDescription());
            }

            bigCover = (ImageView) rootView.findViewById(R.id.coverBig);
            if (bigCover != null) {
                if(Build.VERSION.SDK_INT>=21){
                    Picasso.with(getActivity()).load(mItem.getCover().getSmall()).into(bigCover);
                }else {
                    Picasso.with(getActivity()).load(mItem.getCover().getBig()).into(bigCover);
                }
            }

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

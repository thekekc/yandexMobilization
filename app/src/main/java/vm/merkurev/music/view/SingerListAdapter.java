package vm.merkurev.music.view;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import vm.merkurev.music.R;
import vm.merkurev.music.model.Singer;


public class SingerListAdapter extends BaseAdapter {
    private List<Singer> singers;
    private Context context;
    private int itemLayout;

    public SingerListAdapter(@NonNull List<Singer> singers, @NonNull Context context, int itemLayout) {
        this.singers = singers;
        this.context = context;
        this.itemLayout = itemLayout;
    }


    @Override
    public int getCount() {
        return singers.size();
    }

    @Override
    public Object getItem(int i) {
        return singers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = createView();
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.name.setText(singers.get(i).getName());
        viewHolder.genres.setText(getGenres(singers.get(i).getGenres()));
        String albumsString = context.getResources().getQuantityString(R.plurals.albums,
                singers.get(i).getAlbums(),singers.get(i).getAlbums());
        String tracksString = context.getResources().getQuantityString(R.plurals.tracks,
                singers.get(i).getTracks(),singers.get(i).getTracks());

        viewHolder.amounts.setText(albumsString +", " + tracksString);
        Picasso.with(context).setIndicatorsEnabled(true);
        Picasso.with(context).load(singers.get(i).getCover().getSmall()).into(viewHolder.coverSmall);
        return view;
    }

    private View createView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(itemLayout, null);
        ViewHolder viewHolder = new ViewHolder();
        TextView name = (TextView) view.findViewById(R.id.name);
        if (name != null) {
            viewHolder.name = name;
        }
        TextView genres = (TextView) view.findViewById(R.id.genres);
        if (genres != null) {
            viewHolder.genres = genres;
        }
        TextView amounts = (TextView) view.findViewById(R.id.amounts);
        if (amounts != null) {
            viewHolder.amounts = amounts;
        }
        ImageView coverSmall = (ImageView) view.findViewById(R.id.coverSmall);
        if (coverSmall != null) {
            viewHolder.coverSmall = coverSmall;
        }
        view.setTag(viewHolder);
        return view;
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


    private class ViewHolder {
        private TextView name;
        private TextView genres;
        private TextView amounts;
        private ImageView coverSmall;

    }
}

package vm.merkurev.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import vm.merkurev.music.model.ModelListener;
import vm.merkurev.music.model.SingerEntity;
import vm.merkurev.music.model.SingersModel;


/**
 * An activity representing a list of Singers. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SingerDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link SingerListFragment} and the item details
 * (if present) is a {@link SingerDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link SingerListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class SingerListActivity extends AppCompatActivity
        implements SingerListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_list);

        if (findViewById(R.id.singer_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((SingerListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.singer_list))
                    .setActivateOnItemClick(true);
        }

    }

    /**
     * Callback method from {@link SingerListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(SingerEntity entity) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
//            arguments.putString(SingerDetailFragment.ARG_ITEM_ID, id);
            arguments.putSerializable(SingerDetailFragment.ARG_ITEM_ID, entity);
            SingerDetailFragment fragment = new SingerDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.singer_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, SingerDetailActivity.class);
            detailIntent.putExtra(SingerDetailFragment.ARG_ITEM_ID, entity);
            startActivity(detailIntent);
        }
    }
}

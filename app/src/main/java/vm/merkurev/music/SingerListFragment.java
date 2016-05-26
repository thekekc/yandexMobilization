package vm.merkurev.music;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicLong;

import vm.merkurev.music.model.Singer;
import vm.merkurev.music.presenter.IListViewPresenter;
import vm.merkurev.music.presenter.ListViewPresenter;
import vm.merkurev.music.view.IListView;
import vm.merkurev.music.view.SingerListAdapter;

/**
 * A list fragment representing a list of Singerss. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link SingerDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class SingerListFragment extends ListFragment implements IListView {


    /**
     * Presenter that takes care of all view manipulation
     */
    private IListViewPresenter presenter;


    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        void onItemSelected(Singer entity, View holder);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(Singer entity, View holder) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SingerListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create presenter
        presenter = new ListViewPresenter(this, getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText(getActivity().getString(R.string.empty));
        presenter.onCreate();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        getListView().setItemChecked(position, true);
        presenter.itemSelected(position, view);
    }

    @Override
    public void updateList() {
        if (getListAdapter() == null) {
            //set custom adapter for listView
            setListAdapter(new SingerListAdapter(presenter.getSingers(), this.getActivity(), R.layout.singer_list_item));
        }
        SingerListAdapter singerListAdapter = (SingerListAdapter) getListAdapter();
        singerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(int position, View view) {
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(presenter.getSingers().get(position), view);
    }


    @Override
    public void setActiveItem(int position) {
        //Item check works only in pads
        getListView().setItemChecked(position, true);
    }

    @Override
    public int getFirstVisible() {
        return getListView().getFirstVisiblePosition();
    }

    @Override
    public int getPadding() {
        View v = getListView().getChildAt(0);
        //returns padding from top of first item, if it is not fully shown,
        //to return scroll in last seen position
        return (v == null) ? 0 : (v.getTop() - getListView().getPaddingTop());
    }

    @Override
    public void scrollToPosition(final int position, final int padding) {
        //hack way, to make list scroll to saved position after application is shut down
        //if activity is just recreated everything works fine without post
        getListView().post(new Runnable() {
            @Override
            public void run() {
                getListView().setSelectionFromTop(position, padding);
            }
        });
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

}

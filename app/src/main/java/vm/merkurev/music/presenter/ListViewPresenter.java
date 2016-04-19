package vm.merkurev.music.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vm.merkurev.music.R;
import vm.merkurev.music.appContainer.AppContainer;
import vm.merkurev.music.model.ModelListener;
import vm.merkurev.music.model.NetworkSingerModel;
import vm.merkurev.music.model.Singer;
import vm.merkurev.music.model.cache.ICache;
import vm.merkurev.music.view.IListView;

/**
 * Created by merkurev on 08.04.16.
 */
public class ListViewPresenter implements IListViewPresenter {

    private static final String APP_PREFERENCES = "active_item";
    private static final String ACTIVE_POSITION = "position";
    private static final String FIRST_VISIBLE_POSITION = "first_visible_position";
    private static final String PADDING = "padding";
    private IListView listView;
    private final List<Singer> singers = new ArrayList<>();
    private boolean isAttached = false;
    private NetworkSingerModel networkSingerModel;
    private ICache fileCache;
    private AppContainer appContainer;
    private int activeItem  = -1;
    private int firstVisiblePosition = -1;
    private int padding = 0;

    private ModelListener modelListener = new ModelListener() {
        @Override
        public void onUpdate() {
            singers.clear();
            singers.addAll(networkSingerModel.getDataList());
            fileCache.putInCache(singers);
            if (isAttached){
                listView.updateList();
                listView.setActiveItem(activeItem);
                listView.scrollToPosition(firstVisiblePosition, padding);
            }
        }

        @Override
        public void onError() {
            if(isAttached) listView.showError(appContainer.getString(R.string.NETWORK_ERROR));
        }
    };
    public ListViewPresenter(IListView listView, Activity activity){
        this.listView = listView;
        appContainer = (AppContainer) activity.getApplication();
        fileCache = appContainer.getFileCache();
        networkSingerModel = appContainer.getNetworkSingerModel();
    }

    @Override
    public void onCreate() {
        isAttached = true;
        networkSingerModel.addListener(modelListener);
        //if we have singers from network don't update them
        //until app restart
        singers.clear();
        if(networkSingerModel.getDataList().size()>0){
            singers.addAll(networkSingerModel.getDataList());
        } else {
            networkSingerModel.updateSingers();
            singers.addAll(fileCache.getFromCache());
        }
        activeItem = loadPosition(ACTIVE_POSITION);
        firstVisiblePosition = loadPosition(FIRST_VISIBLE_POSITION);
        padding = loadPosition(PADDING);
        listView.updateList();
        listView.setActiveItem(activeItem);
        listView.scrollToPosition(firstVisiblePosition, padding);
    }

    @Override
    public void onResume() {
        listView.scrollToPosition(firstVisiblePosition, padding);
    }

    @Override
    public void onDestroy() {
        isAttached = false;
        networkSingerModel.removeListener(modelListener);
        saveScrollPosition();
    }

    @Override
    public void itemSelected(int position, View view) {
        listView.showDetails(position, view);
        activeItem = position;
        savePosition(activeItem, ACTIVE_POSITION);
        saveScrollPosition();
    }


    @Override
    public List<Singer> getSingers() {
        return singers;
    }

    private void saveScrollPosition(){
        savePosition(listView.getFirstVisible(), FIRST_VISIBLE_POSITION);
        savePosition(listView.getPadding(), PADDING);
    }

    private void savePosition(int activePosition, String id){
        SharedPreferences preferences = appContainer.getSharedPreferences(
                APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(id, activePosition);
        editor.apply();
    }

    private int loadPosition(String id){
        SharedPreferences preferences = appContainer.getSharedPreferences(
                APP_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getInt(id, -1);
    }

}

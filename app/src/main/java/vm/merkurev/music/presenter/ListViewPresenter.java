package vm.merkurev.music.presenter;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vm.merkurev.music.R;
import vm.merkurev.music.appContainer.AppContainer;
import vm.merkurev.music.model.ModelListener;
import vm.merkurev.music.model.NetworkSingerModel;
import vm.merkurev.music.model.Singer;
import vm.merkurev.music.model.cache.FileCache;
import vm.merkurev.music.model.cache.ICache;
import vm.merkurev.music.view.IListView;

/**
 * Created by merkurev on 08.04.16.
 */
public class ListViewPresenter implements IListViewPresenter {
    private IListView listView;
    private List<Singer> singers = new ArrayList<>();
    private boolean isAttached = false;
    private NetworkSingerModel networkSingerModel;
    private ICache fileCache;
    private AppContainer appContainer;

    private ModelListener modelListener = new ModelListener() {
        @Override
        public void onUpdate() {
            singers  = networkSingerModel.getDataList();
            fileCache.putInCache(singers);
            if (isAttached) listView.setViewList(singers);
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
        if(networkSingerModel.getDataList().size()>0){
            singers = networkSingerModel.getDataList();
        } else {
            networkSingerModel.updateSingers();
            singers = fileCache.getFromCache();
        }
        listView.setViewList(singers);
    }

    @Override
    public void onDestroy() {
        isAttached = false;
        networkSingerModel.removeListener(modelListener);
    }

    @Override
    public void itemSelected(int itemId) {

    }


}

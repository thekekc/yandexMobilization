package vm.merkurev.music.appContainer;

import android.app.Application;

import vm.merkurev.music.model.NetworkSingerModel;
import vm.merkurev.music.model.cache.FileCache;
import vm.merkurev.music.model.cache.ICache;

/**
 * Application container containing global dependencies
 */
public class AppContainer extends Application {
    private NetworkSingerModel networkSingerModel;
    private ICache fileCache;

    public AppContainer() {
        //creating cache and network model
        networkSingerModel = new NetworkSingerModel();
        fileCache = new FileCache(this);
    }

    /**
     * returns network model containing list of singers
     *
     * @return NetworkSingerModel
     */
    public NetworkSingerModel getNetworkSingerModel() {
        return networkSingerModel;
    }

    /**
     * returns file cache of singers
     *
     * @return ICache
     */

    public ICache getFileCache() {
        return fileCache;
    }
}

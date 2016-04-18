package vm.merkurev.music.appContainer;

import android.app.Application;
import vm.merkurev.music.model.NetworkSingerModel;
import vm.merkurev.music.model.cache.FileCache;
import vm.merkurev.music.model.cache.ICache;

/**
 * Created by merkurev on 03.04.16.
 */
public class AppContainer extends Application {
    private NetworkSingerModel networkSingerModel;
    private ICache fileCache;

    public AppContainer() {
        networkSingerModel = new NetworkSingerModel();
        fileCache = new FileCache(this);
    }

    public NetworkSingerModel getNetworkSingerModel() {
        return networkSingerModel;
    }

    public ICache getFileCache() {
        return fileCache;
    }
}

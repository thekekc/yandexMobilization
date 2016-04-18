package vm.merkurev.music.model.cache;

import java.util.List;

import vm.merkurev.music.model.Singer;

/**
 * Created by merkurev on 25.03.16.
 */
public interface ICache {
    void putInCache(List<Singer> cacheObject);
    List<Singer> getFromCache();
    void invalidate();

}

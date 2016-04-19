package vm.merkurev.music.model.cache;

import java.util.List;

import vm.merkurev.music.model.Singer;

/**
 * Cache interface
 */
public interface ICache {
    void putInCache(List<Singer> cacheObject);

    List<Singer> getFromCache();

    void invalidate();

}

package vm.merkurev.music.model.cache;

/**
 * Created by merkurev on 25.03.16.
 */
public interface ICache<T> {
    void putInCahe(T cacheObject);
    T getFromCache();

}

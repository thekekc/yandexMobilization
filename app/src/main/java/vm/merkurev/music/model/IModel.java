package vm.merkurev.music.model;

/**
 * Created by merkurev on 25.03.16.
 */
public interface IModel {
    void addListener(ModelListener modelListener);
    void removeListener(ModelListener modelListener);
}

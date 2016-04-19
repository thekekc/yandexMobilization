package vm.merkurev.music.model;

/**
 * IModel interface creating methods to work with listeners
 */
public interface IModel {
    void addListener(ModelListener modelListener);

    void removeListener(ModelListener modelListener);
}

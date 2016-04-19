package vm.merkurev.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract realization of IModel, working with listeners array
 */
public class Model implements IModel {
    private final List<ModelListener> listeners = new ArrayList<>();

    @Override
    public void addListener(ModelListener modelListener) {
        listeners.add(modelListener);
    }

    @Override
    public void removeListener(ModelListener modelListener) {
        listeners.remove(modelListener);
    }

    protected void notifyListeners() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onUpdate();
        }
    }

    protected void notifyListenersError() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onError();
        }

    }
}

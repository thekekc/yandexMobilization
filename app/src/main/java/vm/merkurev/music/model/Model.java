package vm.merkurev.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by merkurev on 25.03.16.
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
    protected void notifyListeners(){
        if (listeners!=null){
            for (int i = 0; i < listeners.size(); i++) {
                listeners.get(i).onUpdate();
            }
        }
    }
    protected void notifyListenersError(){
        if (listeners!=null){
            for (int i = 0; i < listeners.size(); i++) {
                listeners.get(i).onError();
            }
        }

    }
}

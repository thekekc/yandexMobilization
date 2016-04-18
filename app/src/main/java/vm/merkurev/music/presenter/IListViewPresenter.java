package vm.merkurev.music.presenter;

import java.util.List;
import vm.merkurev.music.model.Singer;

/**
 * Created by merkurev on 08.04.16.
 */
public interface IListViewPresenter {
    void onCreate();
    void onDestroy();
    void itemSelected(int itemId);
    List<Singer> getSingers();
}

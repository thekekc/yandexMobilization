package vm.merkurev.music.presenter;

import android.view.View;

import java.util.List;
import vm.merkurev.music.model.Singer;

/**
 * Created by merkurev on 08.04.16.
 */
public interface IListViewPresenter {
    void onCreate();
    void onDestroy();
    void onResume();
    void itemSelected(int position, View view);
    List<Singer> getSingers();
}

package vm.merkurev.music.presenter;

import android.view.View;

import java.util.List;

import vm.merkurev.music.model.Singer;

/**
 * Presenter interface
 */
public interface IListViewPresenter {
    void onCreate();

    void onStop();

    void itemSelected(int position, View view);

    List<Singer> getSingers();
}

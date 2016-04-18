package vm.merkurev.music.presenter;

/**
 * Created by merkurev on 08.04.16.
 */
public interface IListViewPresenter {
    void onCreate();
    void onDestroy();
    void itemSelected(int itemId);
}

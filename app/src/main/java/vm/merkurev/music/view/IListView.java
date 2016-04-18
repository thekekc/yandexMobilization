package vm.merkurev.music.view;

import java.util.List;

import vm.merkurev.music.model.Singer;

/**
 * Created by merkurev on 24.03.16.
 */
public interface IListView  {
    void setViewList(List<Singer> singers);
    void showError(String message);
}

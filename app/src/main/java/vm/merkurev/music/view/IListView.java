package vm.merkurev.music.view;

import java.util.List;

import vm.merkurev.music.model.SingerEntity;

/**
 * Created by merkurev on 24.03.16.
 */
public interface IListView  {
    void setViewList(List<SingerEntity> singers);
    SingerEntity onItemSelect();
    void showError(String message);
}

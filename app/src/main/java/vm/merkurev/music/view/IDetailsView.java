package vm.merkurev.music.view;

import vm.merkurev.music.model.SingerEntity;

/**
 * Created by merkurev on 24.03.16.
 */
public interface IDetailsView {
    void setDetails(SingerEntity singer);
    void onBackButtonPressed();
    void showError(String message);
}

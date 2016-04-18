package vm.merkurev.music.view;

import vm.merkurev.music.model.Singer;

/**
 * Created by merkurev on 24.03.16.
 */
public interface IDetailsView {
    void setDetails(Singer singer);
    void onBackButtonPressed();
    void showError(String message);
}

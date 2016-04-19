package vm.merkurev.music.view;

import android.view.View;


/**
 * ListView interface
 */
public interface IListView {
    void updateList();

    void showError(String message);

    void showDetails(int position, View view);

    void setActiveItem(int position);

    void scrollToPosition(int position, int padding);

    int getFirstVisible();

    int getPadding();
}

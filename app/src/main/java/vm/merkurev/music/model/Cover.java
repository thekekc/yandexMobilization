package vm.merkurev.music.model;

import java.io.Serializable;

/**
 * Created by merkurev on 26.03.16.
 */
public class Cover implements Serializable {
    String small;
    String big;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }
}

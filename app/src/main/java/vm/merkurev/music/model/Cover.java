package vm.merkurev.music.model;

import java.io.Serializable;

/**
 * Serializable Cover model, containing two cover images
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

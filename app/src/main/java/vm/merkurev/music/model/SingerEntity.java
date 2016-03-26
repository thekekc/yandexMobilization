package vm.merkurev.music.model;

import java.util.List;

/**
 * Created by merkurev on 24.03.16.
 */
public class SingerEntity {
    private Integer id;
    private java.lang.String avatarUrl;
    private java.lang.String name;
    private int songNum;
    private int albumNum;
    private List<String> genres;
    private java.lang.String link;
    private java.lang.String bio;



    public java.lang.String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(java.lang.String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public int getSongNum() {
        return songNum;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    public int getAlbumNum() {
        return albumNum;
    }

    public void setAlbumNum(int albumNum) {
        this.albumNum = albumNum;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public java.lang.String getBio() {
        return bio;
    }

    public void setBio(java.lang.String bio) {
        this.bio = bio;
    }
}

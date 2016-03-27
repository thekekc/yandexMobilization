package vm.merkurev.music.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by merkurev on 24.03.16.
 */
public class SingerEntity {
    private Integer id;
    private Cover cover;
    @SerializedName("name")
    @Expose
    private java.lang.String name;
    private Integer tracks;
    private Integer albums;
    private List<java.lang.String> genres;
    private java.lang.String link;
    @SerializedName("description")
    private java.lang.String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Integer getTracks() {
        return tracks;
    }

    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    public Integer getAlbums() {
        return albums;
    }

    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    public List<java.lang.String> getGenres() {
        return genres;
    }
//
    public void setGenres(List<java.lang.String> genres) {
        this.genres = genres;
    }
//

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }
//
    public java.lang.String getLink() {
        return link;
    }

    public void setLink(java.lang.String link) {
        this.link = link;
    }
//
    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }
}

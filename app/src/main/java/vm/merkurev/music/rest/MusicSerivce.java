package vm.merkurev.music.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vm.merkurev.music.model.Singer;

/**
 * Created by merkurev on 25.03.16.
 */
public interface MusicSerivce {
    @GET("artists.json")
    Call<List<Singer>> listSingers();
}

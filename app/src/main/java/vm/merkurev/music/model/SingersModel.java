package vm.merkurev.music.model;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import vm.merkurev.music.model.cache.ICache;
import vm.merkurev.music.rest.MusicSerivce;

/**
 * Created by merkurev on 25.03.16.
 */
public class SingersModel extends Model {
    private final List<SingerEntity> singers = new ArrayList<>();
    private final Retrofit retrofit;
    private MusicSerivce musicSerivce;
    private ICache<List<SingerEntity>> singersCache;
    public SingersModel(ICache<List<SingerEntity>> singersCache){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://download.cdn.yandex.net/mobilization-2016/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        musicSerivce = retrofit.create(MusicSerivce.class);
        this.singersCache = singersCache;
        //singers = singersCache.getFromCache();
    }

    public List<SingerEntity> getSingers(){
      return singers;
    }


    public void updateSingers(){
        musicSerivce.listSingers().enqueue(new Callback<List<SingerEntity>>() {
            @Override
            public void onResponse(Call<List<SingerEntity>> call, Response<List<SingerEntity>> response) {
                singers.clear();
                singers.addAll(response.body());
                notifyListeners();
            }

            @Override
            public void onFailure(Call<List<SingerEntity>> call, Throwable t) {
                notifyListenersError();
            }
        });
    }
}

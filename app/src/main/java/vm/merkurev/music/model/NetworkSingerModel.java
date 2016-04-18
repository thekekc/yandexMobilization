package vm.merkurev.music.model;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import vm.merkurev.music.model.cache.ICache;

/**
 * Created by merkurev on 25.03.16.
 */
public class NetworkSingerModel extends Model {
    private final List<Singer> tArrayList = new ArrayList<>();
    private final String URL = "http://download.cdn.yandex.net/mobilization-2016/";
    private final Retrofit retrofit;
    private Service service;
    public NetworkSingerModel(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
    }

    public List<Singer> getDataList(){
      return tArrayList;
    }


    public void updateSingers(){
        service.list().enqueue(new Callback<List<Singer>>() {
            @Override
            public void onResponse(Call<List<Singer>> call, Response<List<Singer>> response) {
                tArrayList.clear();
                tArrayList.addAll(response.body());
                notifyListeners();
            }

            @Override
            public void onFailure(Call<List<Singer>> call, Throwable t) {
                notifyListenersError();
            }
        });
    }

    private interface Service{
        @GET("artists.json")
        Call<List<Singer>> list();
    }
}

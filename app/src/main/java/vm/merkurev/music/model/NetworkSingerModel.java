package vm.merkurev.music.model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Network Model recieveing data from network using retrofit library
 */
public class NetworkSingerModel extends Model {
    private final List<Singer> tArrayList = new ArrayList<>();
    /**
     * yandex mobilization url
     */
    private final static String URL = "http://download.cdn.yandex.net/mobilization-2016/";
    private Service service;

    public NetworkSingerModel() {
        //creating retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
    }

    /**
     * get singers
     *
     * @return List<Singer>
     */
    public List<Singer> getDataList() {
        return tArrayList;
    }


    /**
     * method that starts network download of singers
     * after download it calls ModelListener callback
     */
    public void updateSingers() {
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

    /*
     * retrofit interface to get objects of specified class
     */
    private interface Service {
        @GET("artists.json")
        Call<List<Singer>> list();
    }
}

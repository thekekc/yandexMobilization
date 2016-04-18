package vm.merkurev.music.model;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import vm.merkurev.music.SingerListActivity;
import vm.merkurev.music.model.cache.FileCache;
import vm.merkurev.music.model.cache.ICache;

@RunWith(AndroidJUnit4.class)
public class CacheTest extends ActivityInstrumentationTestCase2 {
    private ICache cache;
    private Context context;
    private List<Singer> singersPut = new ArrayList<>();


    public CacheTest() {
        super(SingerListActivity.class);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        getInstrumentation().waitForIdleSync();
        Instrumentation instrumentation = getInstrumentation();
       // context = getInstrumentation().getContext().getApplicationContext();
        context = getActivity();
        cache = new FileCache(context);
        Singer singer = new Singer();
        singer.setName("VM");
        singer.setAlbums(12);
        singer.setTracks(12);
        singer.setDescription("asdfsadf");
        singer.setGenres(new ArrayList<String>());
        singer.setCover(new Cover());
        singersPut.add(singer);
        cache.putInCache(singersPut);
    }

    @Test
    public void putAndGetTest() {
        List<Singer> singersGet = cache.getFromCache();
        Assert.assertNotNull(singersGet);
        Assert.assertTrue(singersGet.get(0).getName().equals("VM"));
        cache.invalidate();
        Assert.assertTrue(cache.getFromCache().size() == 0);
    }

}

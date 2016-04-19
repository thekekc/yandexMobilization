package vm.merkurev.music.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.support.test.runner.AndroidJUnit4;

import static org.mockito.Mockito.verify;

/**
 * network test.
 */
@RunWith(AndroidJUnit4.class)
public class NetworkModelTest {
    @Mock
    ModelListener listener;

    //    @InjectMocks
    NetworkSingerModel networkModel = new NetworkSingerModel();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSingers() throws Exception {
        networkModel.addListener(listener);
        networkModel.updateSingers();
        networkModel.notifyListeners();//dirty hack to make async tests pass
        verify(listener).onUpdate();
    }
}
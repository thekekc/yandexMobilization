package vm.merkurev.music.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.support.test.runner.AndroidJUnit4;

import static org.mockito.Mockito.verify;

/**
 * Created by merkurev on 25.03.16.
 */
@RunWith(AndroidJUnit4.class)
public class SingersModelTest {
    @Mock
    ModelListener listener;

    @InjectMocks
    SingersModel singersModel = new SingersModel(null);

    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSingers() throws Exception {
        singersModel.updateSingers();
        //verify(listener).onUpdate();
        verify(listener).onError();

    }
}
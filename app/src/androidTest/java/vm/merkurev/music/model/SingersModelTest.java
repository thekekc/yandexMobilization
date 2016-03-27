package vm.merkurev.music.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import android.support.test.runner.AndroidJUnit4;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * Created by merkurev on 25.03.16.
 */
@RunWith(AndroidJUnit4.class)
public class SingersModelTest {
    @Mock
    ModelListener listener;

//    @InjectMocks
    SingersModel singersModel = new SingersModel(null);

    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSingers() throws Exception {
        singersModel.addListener(listener);
        singersModel.updateSingers();

        verify(listener,timeout(1000)).onUpdate();
        verify(listener,timeout(1000)).onError();
    }
}
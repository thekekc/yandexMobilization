package vm.merkurev.music;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;
import android.media.Ringtone;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Handler;

/**
 * Created by merkurev on 02.04.16.
 */
@RunWith(AndroidJUnit4.class)
public class ListClickCase extends ActivityInstrumentationTestCase2{


    private Activity activity;
    public ListClickCase() {
        super(SingerListActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
        getInstrumentation().waitForIdleSync();
    }

    @Test
    public void onListViewItemClick(){
        Instrumentation instrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor activityMonitor = instrumentation.addMonitor(SingerDetailActivity.class.getName(), null, false);

        final ListView listView = (ListView) activity.findViewById(android.R.id.list);
        assertNotNull("list has not appeared", listView);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getAdapter().getView(0, null, null), 0, listView.getItemIdAtPosition(0));
            }
        });
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Test
    public void rotationTest(){
       activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    private void test(){

    }
}

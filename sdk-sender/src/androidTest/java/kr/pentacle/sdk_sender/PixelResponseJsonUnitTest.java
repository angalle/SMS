package kr.pentacle.sdk_sender;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.os.Bundle;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PixelResponseJsonUnitTest {

    String one_destination_json = "";
    String two_destination_json = "";
    String three_destination_json = "";

    @Before
    public void initialize() {
        one_destination_json = MockingPixelData.PIXEL_RESPONSE_ONE_DESTINATION;
        two_destination_json = MockingPixelData.PIXEL_RESPONSE_TWO_DESTINATION;
        three_destination_json = MockingPixelData.PIXEL_RESPONSE_THREE_DESTINATION;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCorrectOneDestination() {
        HyperDMPJsonResponse JsonParser = new HyperDMPJsonResponse(one_destination_json);
        List<Bundle> data = JsonParser.getAllParameters();

        for(Bundle item: data){
            assertNotNull(item.get("trigger"));
            assertNotNull(item.get("segmentId"));
        }

        assertEquals(3, data.size());
    }

    @Test
    public void testCorrectTwoDestination() {
        HyperDMPJsonResponse JsonParser = new HyperDMPJsonResponse(two_destination_json);
        List<Bundle> data = JsonParser.getAllParameters();

        for(Bundle item: data){
            Log.i("test", item.toString());
            assertNotNull(item.get("trigger"));
            assertNotNull(item.get("segmentId"));
        }
        assertEquals(3, data.size());
    }

    @Test
    public void testCorrectThreeDestination() {
        HyperDMPJsonResponse JsonParser = new HyperDMPJsonResponse(three_destination_json);
        List<Bundle> data = JsonParser.getAllParameters();

        for(Bundle item: data){
            Log.i("test", item.toString());
            assertNotNull(item.get("trigger"));
            assertNotNull(item.get("segmentId"));
        }

        assertEquals(3, data.size());
    }


}
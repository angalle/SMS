package kr.pentacle.sdk_sender;


import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PixelResponseUnitTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test_correct_container_id() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("hyper_dmp_container_id", 1);
        String containerId = HyperDMPUtils.getContainerId(dataMap);

        assertEquals("1", containerId);
    }

    @Test
    public void test_incorrect_container_id_letter() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("getContainerId:: No Supply Container ID Key");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("hyper_dmp_container_id", "GTM");
        String containerId = HyperDMPUtils.getContainerId(dataMap);

    }

    @Test
    public void test_incorrect_container_id_null() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("getContainerId:: No Supply Container ID Key");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("hyper_dmp_container_id", null);
        String containerId = HyperDMPUtils.getContainerId(dataMap);
    }

    @Test
    public void test_incorrect_container_id_no_key() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("getContainerId:: No Supply Container ID Key");

        Map<String, Object> dataMap = new HashMap<>();
        String containerId = HyperDMPUtils.getContainerId(dataMap);
    }

}
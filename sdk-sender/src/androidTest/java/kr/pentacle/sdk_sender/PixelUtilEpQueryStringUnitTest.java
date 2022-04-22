package kr.pentacle.sdk_sender;


import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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
public class PixelUtilEpQueryStringUnitTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * getEPQueryString Testing
     * @throws Exception
     */

    @Test
    public void testCorrectEpQueryStringOneParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("ep_page_view", "MainView");
        String queryString = HyperDMPUtils.getEPQueryString(dataMap);

        assertEquals("ep=MainView", queryString);
    }

    @Test
    public void testCorrectEpQueryStringTwoParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("ep_page_view", "MainView");
        dataMap.put("ep_price", 5000);
        String queryString = HyperDMPUtils.getEPQueryString(dataMap);

        assertEquals("ep=MainView&ep=5000", queryString);
    }

    @Test
    public void testCorrectEpQueryStringTwoParameterUnCorrectOneParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("ep_page_view", "MainView");
        dataMap.put("ep_price", 5000);
        dataMap.put("click", "target");
        String queryString = HyperDMPUtils.getEPQueryString(dataMap);

        assertEquals("ep=MainView&ep=5000", queryString);
    }

    @Test
    public void testCorrectEpQueryStringNothing() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        String queryString = HyperDMPUtils.getEPQueryString(dataMap);
        assertEquals("", queryString);
    }

    @Test
    public void testCorrectEpQueryStringNothingUnCorrectOneParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        String queryString = HyperDMPUtils.getEPQueryString(dataMap);
        dataMap.put("click", "target");
        assertEquals("", queryString);
    }


    /**
     * getDynamicQueryString Testing
     * @throws Exception
     */
    @Test
    public void testCorrectDynamicQueryStringOneParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("page_view", "MainView");
        String queryString = HyperDMPUtils.getDynamicQueryString(dataMap);

        assertEquals("page_view=MainView", queryString);
    }

    @Test
    public void testCorrectDynamicQueryStringTwoParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("page_view", "MainView");
        dataMap.put("price", 5000);
        String queryString = HyperDMPUtils.getDynamicQueryString(dataMap);

        Assert.assertThat(queryString, CoreMatchers.containsString("price=5000"));
        Assert.assertThat(queryString, CoreMatchers.containsString("page_view=MainView"));
    }

    @Test
    public void testCorrectDynamicQueryStringTwoParameterUnCorrectOneParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("page_view", "MainView");
        dataMap.put("price", 5000);
        dataMap.put("ep_click", "target");
        String queryString = HyperDMPUtils.getDynamicQueryString(dataMap);

        Assert.assertThat(queryString, CoreMatchers.containsString("price=5000"));
        Assert.assertThat(queryString, CoreMatchers.containsString("page_view=MainView"));
    }

    @Test
    public void testCorrectDynamicQueryStringNothing() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        String queryString = HyperDMPUtils.getDynamicQueryString(dataMap);
        assertEquals("", queryString);
    }

    @Test
    public void testCorrectDynamicQueryStringNothingUnCorrectOneParameter() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        String queryString = HyperDMPUtils.getDynamicQueryString(dataMap);
        dataMap.put("ep_click", "target");
        assertEquals("", queryString);
    }

}
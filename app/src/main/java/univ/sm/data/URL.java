package univ.sm.data;

/**
 * Created by heesun on 2017-01-13.
 */
public class URL {

    static public String IP = "http://52.78.186.130";
    /**
     * CALVAN PROCESS URL
     * 52.78.186.130
     */
    static public  String BOARD_URL = IP+":40000";
    static public  String SELECT_CALLVAN = "/selectcallvan";
    static public  String INSERT_CALLVAN = "/insertcallvan";
    static public  String SELECT_ONE_CALLVAN = "/selectcallvaninfo";
    static public  String INSERT_CALLVAN_COMMENT = "/insertcallvancomment";
    static public  String APPROAVAL_CALLVAN = "/callvan_complete";

    /**
     * SHUTTLE PROCESS URL
     * 52.78.186.130
     */
    static public  String SHUTTEL_URL = IP+":40001";
    static public  String SELECT_SHUTTLE_SCHEDULE = "/post_schdule";

}

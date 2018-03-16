package univ.sm.Model;

/**
 * Created by heesun on 2017-01-13.
 */
public class Const {
    public static final int INIT = 0;
    public static final int REFRESH = 1;
    static public String SHARED_LIMETE_LAYOUT = "LIMITE_INTRO_LAYOUT";
    static public String CAN_U_FIRST_1 = "CAN_U_FIRST_1";
    static public String CAN_U_FIRST_2 = "CAN_U_FIRST_2";
    static public String NO = "NO";

    static public String CHEONANSTATION_STR = "Cheonan\nStation";
    static public String TERMINALSTATION_STR = "Terminal\nStation";
    static public String ONYANGSTATION_STR = "Onyang\nStation";


    static public int OPPOSIT = 0;
    static public int REVERSE = 1;
    static public String OPSIT = "0";
    static public String REVSE = "1";

    static public int WEEKDAY = 0;
    static public int SATUREDAY = 1;
    static public int SUNDAY = 2;

    static public int[] CHEONANSTATION = {0,7,8};
    static public int[] TERMINAL = {1,9,10};
    static public int[] ONYANG = {2,2,2};
    //static public int[] CHEONANCAMPUS = {3,3,3};

    static public int CHEONANSTATION_C = 0;
    static public int TERMINAL_C = 1;
    static public int ONYANG_C = 2;
    //static public int CHEONANCAMPUS_C = 3;

    /*SHARED PREPERENCD VARIABLE*/
    static public String SHARED_GCM = "GCM";
    static public String SHARED_REG_ID = "REG_ID";
    static public String SHARED_USER = "USER";
    static public String SHARED_MEMBER_EMAIL = "MEMBER_EMAIL";

    static public String LIST_TITLE = "게시물 목록";
    static public String WRITE_TITLE = "게시물 등록";

    /**
     * SHARED PREPERENCD VARIABLE
     * */
    static public String WRITE_NAME = "WRITE_NAME";
    static public String PASSWD = "PASSWD";
    static public String STUDENT_NO = "STUDENT_NO";
    static public String MEMBER_EMAIL = "MEMBER_EMAIL";
    static public String REG_ID = "REG_ID";
    static public String DEPARTMENT = "DEPARTMENT";
    static public String DEPARTURE = "DEPARTURE";
    static public String DEPARTURE_DETAIL = "DEPARTURE_DETAIL";
    static public String DESTINATION = "DESTINATION";
    static public String DESTINATION_DETAIL = "DESTINATION_DETAIL";
    static public String PASSENGER_NUM = "PASSENGER_NUM";
    static public String WAIT_TIME = "WAIT_TIME";

    /*
     * SETTING INFO
     */
    static public String NOTIKEY = "selectNotice";
    static public String SETINFO = "SETINFO";
    static public String NOTICE = "NOTICE";
    static public String DEFAULTNOTI = "TRUE";
    static public String TRUE = "TRUE";
    static public String FALSE = "FALSE";

    /**
     * CALLCAN PROCESS MSG
     */
    public static String CALLVAN_LOADING_MSG = "콜벤리스트를 불러오는 중입니다....";
    public static String MSG_FAIL = "접속이 되지 않습니다. 인터넷망을 확인하거나 관리자에게 문의하세요.:::";
    public static String MSG_ERROR = "서버에 문제가 생겼습니다. 관리자에게 문의해주세요.";

    /* 옮길 URL*/
    static public  String BASE_URL = "http://52.78.186.130:40002";

    /**
     * CALVAN PROCESS URL
     * 52.78.186.130
     */
    static public  String BOARD_URL = "http://52.78.186.130:40000";
    static public  String SELECT_CALLVAN = "/selectcallvan";
    static public  String INSERT_CALLVAN = "/insertcallvan";
    static public  String SELECT_ONE_CALLVAN = "/selectcallvaninfo";
    static public  String INSERT_CALLVAN_COMMENT = "/insertcallvancomment";
    static public  String APPROAVAL_CALLVAN = "/callvan_complete";




    /**
         000 :  천안아산역
         001 :  천안터미널
         002 :  온양터미널
         003 :  천안캠퍼스

         -------------------------

         WEK : 평일
         SAT : 토요일
         SUN : 일요일
     **/
    static public  String CHEONAN_ASAN_ST_000   = "000";
    static public  String CHEONAN_TERMINAL_ST_001   = "001";
    static public  String ONYANG_CAMPAUSE_ST_002   = "002";
    static public  String CHONAN_CAMPAUSE_ST_003   = "003";

    static public  String WEK       = "WEK";
    static public  String SAT       = "SAT";
    static public  String SUN       = "SUN";


    static public String[] CHEONAN_ASAN = {"아산캠","KTX","천안역","KTX","아산캠"};
    static public String[] CHEONAN_TERMINAL = {"아산캠","","터미널","","아산캠"};
    static public String[] ONYANG_TERMINAL = {"아산캠","온양터미널","온양역","아산캠",""};


    static public final String SENDER_ID = "726031754697";
}

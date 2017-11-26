package univ.sm.data;

public class SplashData {

	public static String register_GCM_AWS = "http://52.79.137.125:40001/register";

	public static String ip = "http://52.78.186.130:40001/shuttle?where=";
	public static String bus_cheonanasan = ip + "cheonanasan"; // 0
	public static String bus_terminal = ip + "terminal"; // 1
	public static String bus_onyang = ip + "onyang"; // 2
	public static String bus_saturdayCheonanAsan = ip + "saturdayCheonanAsan"; // 3
	public static String bus_saturdayTerminal = ip + "saturdayTerminal"; // 4
	public static String bus_sundayCheonanAsan = ip + "sundayCheonanAsan"; // 5
	public static String bus_sundayTerminal = ip + "sundayTerminal"; // 6
	public static String bus_asansunmoon = ip + "busAsanSM"; // 7
	public static String bus_sunmoonasan = ip + "busSMAsan"; // 8
	public static String bus_sunmoonOnyang = ip + "busSMOnyang"; // 9
	public static String info = ip + "info"; // 10
	public static String bus_cheonanCampers = ip + "cheonanCampers"; // 11

	public static String notice_con = "";

	public  static String[] busUrl = {
			bus_cheonanasan, // 0
			bus_terminal, // 1
			bus_onyang, // 2
			bus_cheonanCampers,// 3
			bus_asansunmoon, // 4
			bus_sunmoonasan, // 5
			bus_sunmoonOnyang,// 6

			bus_saturdayCheonanAsan, // 7
			bus_sundayCheonanAsan, // 8

			bus_saturdayTerminal, // 9
			bus_sundayTerminal, // 10

			info, // 11
	};

	public static String getNotice_con() {
		return notice_con;
	}

	public static void setNotice_con(String notice_con) {
		SplashData.notice_con = notice_con;
	}
}



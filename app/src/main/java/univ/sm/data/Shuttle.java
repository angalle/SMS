package univ.sm.data;

import java.io.Serializable;

public class Shuttle implements Serializable {

	public String No;
//	public String b1,b2,b3,b4,b5,b6;
	public String[] b;



	public Shuttle()
	{
//		No = "";
//		b1 = "";
//		b2 = "";
//		b3 = "";
//		b4 = "";
//		b5 = "";
//		b6 = "";

		b = new String[5];
		for(int i =0;i<b.length;i++)
			b[i] = "";
	}

	public String getNo()
	{		
		return No;
	}

	public String[] getB() {
		return b;
	}

//	public String getB1()
//	{
//		return b1;
//	}
//
//	public String getB2()
//	{
//		return b2;
//	}
//
//	public String getB3()
//	{
//		return b3;
//	}
//
//	public String getB4()
//	{
//		return b4;
//	}
//
//	public String getB5()
//	{
//		return b5;
//	}
//	public String getB6()
//	{
//		return b6;
//	}
//
//
//	public void clear()
//	{
//		No = "";
//		b1 = "";
//		b2 = "";
//		b3 = "";
//		b4 = "";
//		b5 = "";
//		b6 = "";
//	}
}

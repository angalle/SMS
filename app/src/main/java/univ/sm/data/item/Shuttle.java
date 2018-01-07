package univ.sm.data.item;

import java.io.Serializable;

public class Shuttle implements Serializable {

	public String NO,		INSERT_DATE;
	public String ST_ONE,	ST_TWO,	ST_THR,	ST_FOR,	ST_FIV,	ST_SIX,	ST_SEV,	ST_EIG,	ST_NIN,	ST_TEN;
	public String[] b;

	public Shuttle(String NO, 		String INSERT_DATE, String ST_ONE, String ST_TWO, String ST_THR,
				   String ST_FOR, 	String ST_FIV, 		String ST_SIX, String ST_SEV, String ST_EIG,
				   String ST_NIN, 	String ST_TEN) {
		this.NO = NO;			this.INSERT_DATE = INSERT_DATE;		this.ST_ONE = ST_ONE;		this.ST_TWO = ST_TWO;
		this.ST_THR = ST_THR;	this.ST_FOR = ST_FOR;				this.ST_FIV = ST_FIV;		this.ST_SIX = ST_SIX;
		this.ST_SEV = ST_SEV;	this.ST_EIG = ST_EIG;				this.ST_NIN = ST_NIN;		this.ST_TEN = ST_TEN;
	}

	public Shuttle()
	{
		b = new String[5];
		for(int i =0;i<b.length;i++)
			b[i] = "";
	}

}

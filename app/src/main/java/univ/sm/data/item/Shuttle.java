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

	public String[] getB() {
		return b;
	}


	public String getNO() {
		return NO;
	}

	public String getNo() {
		return NO;
	}


	public String getINSERT_DATE() {
		return INSERT_DATE;
	}

	public String getST_ONE() {
		return ST_ONE;
	}

	public String getST_TWO() {
		return ST_TWO;
	}

	public String getST_THR() {
		return ST_THR;
	}

	public String getST_FOR() {
		return ST_FOR;
	}

	public String getST_FIV() {
		return ST_FIV;
	}

	public String getST_SIX() {
		return ST_SIX;
	}

	public String getST_SEV() {
		return ST_SEV;
	}

	public String getST_EIG() {
		return ST_EIG;
	}

	public String getST_NIN() {
		return ST_NIN;
	}

	public String getST_TEN() {
		return ST_TEN;
	}
}

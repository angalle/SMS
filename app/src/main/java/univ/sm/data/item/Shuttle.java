package univ.sm.data.item;

import java.io.Serializable;

public class Shuttle implements Serializable {

	public String NO,		INSERT_DATE;
	public String UNIV_CODE,	STOP_SITE_CODE,	WEEKDAY_CODE,	VACATION_FLAG;
	public String ST_ONE,	ST_TWO,	ST_TRE,	ST_FOR,	ST_FIV,	ST_SIX,	ST_SEV,	ST_EIG,	ST_NIN,	ST_TEN;
	public String[] b;



	public Shuttle(String NO, String INSERT_DATE, String ST_ONE, String ST_TWO, String ST_TRE,
				   String ST_FOR, String ST_FIV, String ST_SIX, String ST_SEV, String ST_EIG,
				   String ST_NIN, String ST_TEN) {
		this.NO = NO;			this.INSERT_DATE = INSERT_DATE;		this.ST_ONE = ST_ONE;		this.ST_TWO = ST_TWO;
		this.ST_TRE = ST_TRE;	this.ST_FOR = ST_FOR;				this.ST_FIV = ST_FIV;		this.ST_SIX = ST_SIX;
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

	public String getST_TRE() {
		return ST_TRE;
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


	public void setNO(String NO) {this.NO = NO;}

	public void setINSERT_DATE(String INSERT_DATE) {this.INSERT_DATE = INSERT_DATE;}

	public void setST_ONE(String ST_ONE) {this.ST_ONE = ST_ONE;}

	public void setST_TWO(String ST_TWO) {this.ST_TWO = ST_TWO;}

	public void setST_TRE(String ST_TRE) {this.ST_TRE = ST_TRE;}

	public void setST_FOR(String ST_FOR) {this.ST_FOR = ST_FOR;}

	public void setST_FIV(String ST_FIV) {this.ST_FIV = ST_FIV;}

	public void setST_SIX(String ST_SIX) {this.ST_SIX = ST_SIX;}

	public void setST_SEV(String ST_SEV) {this.ST_SEV = ST_SEV;}

	public void setST_EIG(String ST_EIG) {this.ST_EIG = ST_EIG;}

	public void setST_NIN(String ST_NIN) {this.ST_NIN = ST_NIN;}

	public void setST_TEN(String ST_TEN) {this.ST_TEN = ST_TEN;}

	public void setB(String[] b) {this.b = b;}

	public void setUNIV_CODE(String UNIV_CODE) {
		this.UNIV_CODE = UNIV_CODE;
	}

	public void setSTOP_SITE_CODE(String STOP_SITE_CODE) {
		this.STOP_SITE_CODE = STOP_SITE_CODE;
	}

	public void setWEEKDAY_CODE(String WEEKDAY_CODE) {
		this.WEEKDAY_CODE = WEEKDAY_CODE;
	}

	public void setVACATION_FLAG(String VACATION_FLAG) {
		this.VACATION_FLAG = VACATION_FLAG;
	}

	public String getUNIV_CODE() {
		return UNIV_CODE;
	}

	public String getSTOP_SITE_CODE() {
		return STOP_SITE_CODE;
	}

	public String getWEEKDAY_CODE() {
		return WEEKDAY_CODE;
	}

	public String getVACATION_FLAG() {
		return VACATION_FLAG;
	}
}

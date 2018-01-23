package univ.sm.data.item;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shuttle{

	@SerializedName("NO")
	public String NO;
	@SerializedName("UPDATE_DATE")
	public String UPDATE_DATE;
	@SerializedName("INSERT_DATE")
	public String INSERT_DATE;
	@SerializedName("UNIV_CODE")
	public String UNIV_CODE;
	@SerializedName("STOP_SITE_CODE")
	public String STOP_SITE_CODE;
	@SerializedName("WEEKDAY_CODE")
	public String WEEKDAY_CODE;
	@SerializedName("VACATION_FLAG")
	public String VACATION_FLAG;
	@SerializedName("ST_ONE")
	public String ST_ONE;
	@SerializedName("ST_TWO")
	public String ST_TWO;
	@SerializedName("ST_TRE")
	public String ST_TRE;
	@SerializedName("ST_FOR")
	public String ST_FOR;
	@SerializedName("ST_FIV")
	public String ST_FIV;
	@SerializedName("ST_SIX")
	public String ST_SIX;
	@SerializedName("ST_SEV")
	public String ST_SEV;
	@SerializedName("ST_EIG")
	public String ST_EIG;
	@SerializedName("ST_NIN")
	public String ST_NIN;
	@SerializedName("ST_TEN")
	public String ST_TEN;
	@SerializedName("ST_ELV")
	public String ST_ELV;
	@SerializedName("ST_TWL")
	public String ST_TWL;

	public Shuttle(String NO, String INSERT_DATE, String ST_ONE, String ST_TWO, String ST_TRE,
				   String ST_FOR, String ST_FIV, String ST_SIX, String ST_SEV, String ST_EIG,
				   String ST_NIN, String ST_TEN) {
		this.NO = NO;			this.INSERT_DATE = INSERT_DATE;		this.ST_ONE = ST_ONE;		this.ST_TWO = ST_TWO;
		this.ST_TRE = ST_TRE;	this.ST_FOR = ST_FOR;				this.ST_FIV = ST_FIV;		this.ST_SIX = ST_SIX;
		this.ST_SEV = ST_SEV;	this.ST_EIG = ST_EIG;				this.ST_NIN = ST_NIN;		this.ST_TEN = ST_TEN;
	}

	public Shuttle(){}


	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(String UPDATE_DATE) {
		this.UPDATE_DATE = UPDATE_DATE;
	}

	public String getST_ELV() {
		return ST_ELV;
	}

	public void setST_ELV(String ST_ELV) {
		this.ST_ELV = ST_ELV;
	}

	public String getST_TWL() {
		return ST_TWL;
	}

	public void setST_TWL(String ST_TWL) {
		this.ST_TWL = ST_TWL;
	}


	public String getNO() {
		return NO;
	}

	public void setNO(String NO) {
		this.NO = NO;
	}

	public String getINSERT_DATE() {
		return INSERT_DATE;
	}

	public String getUNIV_CODE() {
		return UNIV_CODE;
	}

	public String getVACATION_FLAG() {
		return VACATION_FLAG;
	}

	public String getST_SIX() {
		return ST_SIX;
	}

	public void setST_SIX(String ST_SIX) {
		this.ST_SIX = ST_SIX;
	}

	public String getST_SEV() {
		return ST_SEV;
	}

	public void setST_SEV(String ST_SEV) {
		this.ST_SEV = ST_SEV;
	}

	public String getST_EIG() {
		return ST_EIG;
	}

	public void setST_EIG(String ST_EIG) {
		this.ST_EIG = ST_EIG;
	}

	public String getST_NIN() {
		return ST_NIN;
	}

	public void setST_NIN(String ST_NIN) {
		this.ST_NIN = ST_NIN;
	}

	public String getST_TEN() {
		return ST_TEN;
	}

	public void setST_TEN(String ST_TEN) {
		this.ST_TEN = ST_TEN;
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


	public void setINSERT_DATE(String INSERT_DATE) {this.INSERT_DATE = INSERT_DATE;}

	public void setST_ONE(String ST_ONE) {this.ST_ONE = ST_ONE;}

	public void setST_TWO(String ST_TWO) {this.ST_TWO = ST_TWO;}

	public void setST_TRE(String ST_TRE) {this.ST_TRE = ST_TRE;}

	public void setST_FOR(String ST_FOR) {this.ST_FOR = ST_FOR;}

	public void setST_FIV(String ST_FIV) {this.ST_FIV = ST_FIV;}


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


	public String getSTOP_SITE_CODE() {
		return STOP_SITE_CODE;
	}

	public String getWEEKDAY_CODE() {
		return WEEKDAY_CODE;
	}

}

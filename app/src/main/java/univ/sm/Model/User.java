package univ.sm.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by PE-LHS on 2018-03-14.
 */

public class User implements Serializable {
    public String MEMBER_EMAIL= "";
    public String MEMBER_PW= "";
    public String MEMBER_DEPATMENET= "";
    public String MEMBER_NAME= "";
    public String MEMBET_ST_NO= "";
    public String MEMEBER_REG_NO= "";
    public String INDIRECT_FLAG= "";

    public User(HashMap<String, Object> parameters) {
        this.MEMBER_EMAIL = (String)parameters.get("MEMBER_EMAIL");
        this.MEMBER_PW = (String)parameters.get("MEMBER_PW");
        this.MEMBER_DEPATMENET = (String)parameters.get("MEMBER_DEPATMENET");
        this.MEMBER_NAME = (String)parameters.get("MEMBER_NAME");
        this.MEMBET_ST_NO = (String)parameters.get("MEMBET_ST_NO");
        this.MEMEBER_REG_NO = (String)parameters.get("MEMEBER_REG_NO");
        this.INDIRECT_FLAG = (String)parameters.get("INDIRECT_FLAG");
    }
}

package com.scarlett.expenditure.web.account;

import java.io.Serializable;

/**
 * 充值来源体
 * @date 2017年3月28日 上午12:20:16
 * @version 1.0
 */
public class FullFrom implements Serializable{

    private static final long serialVersionUID = 7745457385654694234L;
    
    private String from;

    private String id;
    
    private String regPW;
    
    private String tranPW;
    
    private Double num;

    
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegPW() {
        return regPW;
    }

    public void setRegPW(String regPW) {
        this.regPW = regPW;
    }

    public String getTranPW() {
        return tranPW;
    }

    public void setTranPW(String tranPW) {
        this.tranPW = tranPW;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }
    
    
}

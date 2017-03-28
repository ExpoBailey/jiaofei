package com.scarlett.expenditure.web.account.entity;

import java.io.Serializable;

/**
 * 充值来源体
 * @date 2017年3月28日 上午12:20:16
 * @version 1.0
 */
public class FullFrom implements Serializable{

    private static final long serialVersionUID = 7745457385654694234L;
//    如：总额 70 元    电费     支出   50.0 元   余额 20.0 元
//    如：总额  0 元   支付宝    充值   30.0 元   余额 30.0 元

    /**交易目标*/
    private String from;
    /**来源帐号*/
    private String id;
    /**来源登录密码*/
    private String regPW;
    /**来源交易密码*/
    private String tranPW;

    /**总额*/
    private Double sum;

    /**交易金额*/
    private Double num;

    /**交易后余额*/
    private Double now;

    /**收入还是支出状态*/
    private String type;


    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getNow() {
        return now;
    }

    public void setNow(Double now) {
        this.now = now;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

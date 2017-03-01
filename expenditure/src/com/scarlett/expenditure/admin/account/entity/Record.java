package com.scarlett.expenditure.admin.account.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *Record.java 
 *@intention 
 * <p> 付费易帐户交易记录的实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_ACC_RECORD") 
public class Record implements Serializable{
    private static final long serialVersionUID = 834165068206560678L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="FFY_ID")
    private FuFeiYi ffy;
    @Column(name="CONTENT", length=500)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TRAN_DATE")
    private Date tranDate;
    @Column(name="REMARK", length=500)
    private String remark;
    /** getter and setter method */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public FuFeiYi getFfy() {
        return ffy;
    }
    public void setFfy(FuFeiYi ffy) {
        this.ffy = ffy;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getTranDate() {
        return tranDate;
    }
    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}

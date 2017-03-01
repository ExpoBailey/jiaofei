package com.scarlett.expenditure.admin.account.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.scarlett.expenditure.admin.identity.entity.User;

/**
 *FuFeiYi.java 
 *@intention 
 * <p> 付费易帐户的实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_ACC_FFY") 
public class FuFeiYi implements Serializable{
    private static final long serialVersionUID = 834165068206560678L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @OneToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="USER_ID", unique=true)
    private User user;
    @Column(name="PASSWORD", length=50)
    private String password;
    
    @Column(name="SUM")
    private Double sum;
    @Column(name="REMARK", length=500)
    private String remark;
    
    /** getter and setter method */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Double getSum() {
        return sum;
    }
    public void setSum(Double sum) {
        this.sum = sum;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}

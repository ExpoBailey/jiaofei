package com.scarlett.expenditure.admin.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.scarlett.expenditure.admin.identity.entity.User;

/**
 *Bill.java 
 *@intention 
 * <p> 帐单实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_BUS_BILL") 
public class Bill implements Serializable{
    private static final long serialVersionUID = 108820527041608568L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Company.class)
    @JoinColumn(name="COMPANY_ID", referencedColumnName="ID",
                foreignKey=@ForeignKey(name="FK_BILL_COMPANY"))
    private Company company;
    
    @Column(name="USAGE_AMOUNT")
    private Double usageAmount;
    
    @Column(name="SUM_PRICE")
    private Double sumPrice;
    
    @Column(name="APPEAR_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appearDate;
    
    /** 帐单状态    0：未缴    1：已缴 */
    @Column(name="TYPE")
    private int type;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="PERTAIN", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_BILL_PERTAIN"))
    private User pertain;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="HANDLE_DATE")
    private Date handleDate;
    
    @Column(name="COST_STYLE", length=50)
    private String costStyle;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="CHECKER", referencedColumnName="USER_ID",
    foreignKey=@ForeignKey(name="FK_BILL_CHECKER"))
    private User checker;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CHECK_DATE")
    private Date checkDate;
    
    @Column(name="REMARK", length=500)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double getUsageAmount() {
        return usageAmount;
    }

    public void setUsageAmount(Double usageAmount) {
        this.usageAmount = usageAmount;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Date getAppearDate() {
        return appearDate;
    }

    public void setAppearDate(Date appearDate) {
        this.appearDate = appearDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getPertain() {
        return pertain;
    }

    public void setPertain(User pertain) {
        this.pertain = pertain;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public String getCostStyle() {
        return costStyle;
    }

    public void setCostStyle(String costStyle) {
        this.costStyle = costStyle;
    }

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

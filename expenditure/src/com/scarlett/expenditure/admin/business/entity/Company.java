package com.scarlett.expenditure.admin.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *Company.java 
 *@intention 
 * <p> 出账公司的实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_BUS_COMPANY") 
public class Company implements Serializable{
    private static final long serialVersionUID = 834165068206560678L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    /** 帐单类型   */
    @Column(name="TYPE", length=50)
    private String type;
    
    @Column(name="NAME", length=100)
    private String name;
    
    @Column(name="PRICE")
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}

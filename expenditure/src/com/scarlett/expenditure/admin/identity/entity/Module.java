package com.scarlett.expenditure.admin.identity.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *Module.java 
 *@intention 
 * <p> 操作实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_ID_MODULE")
public class Module implements Serializable{
    private static final long serialVersionUID = 5698207441687246967L;
    /*
     * PK主键由系统自动生成
        (0001...0002)四位为模块;
        (00010001..)八位为操作
     * */
    @Id 
    @Column(name="CODE", length=100)
    private String code;
    @Column(name="NAME", length=50)
    private String name;
    @Column(name="URL", length=100)
    private String url;
    @Column(name="REMARK", length=500)
    private String remark;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="CREATER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_MODULE_CREATER"))
    private User creater;
    @Column(name="CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="MODIFIER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_MODULE_MODIFIER"))
    private User modifier;
    @Column(name="MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    
    /* getter and setter method */    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public User getCreater() {
        return creater;
    }
    public void setCreater(User creater) {
        this.creater = creater;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public User getModifier() {
        return modifier;
    }
    public void setModifier(User modifier) {
        this.modifier = modifier;
    }
    public Date getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

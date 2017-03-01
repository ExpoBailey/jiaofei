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
 *Popedom.java 
 *@intention 
 * <p> 权限实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_ID_POPEDOM")
public class Popedom implements Serializable{
    private static final long serialVersionUID = 5698207441687246967L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name="ID")
    private Long id;
    /** 权限与模块存在N-1关联 */
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Module.class)
    @JoinColumn(name="MODULE_CODE", referencedColumnName="CODE",
                foreignKey=@ForeignKey(name="FK_POPEDOM_MODULE")) // 更改外键名
    private Module module;
    /** 权限与角色存在N-1关联  */
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Module.class)
    @JoinColumn(name="OPERA_CODE", referencedColumnName="CODE",
                foreignKey=@ForeignKey(name="FK_POPEDOM_OPERA")) // 更改外键名
    private Module opera;
    
    /** 权限与角色存在多对一的关系 */
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Role.class)
    @JoinColumn(name="ROLE_ID", referencedColumnName="ID",
                foreignKey=@ForeignKey(name="FK_POPEDOM_ROLE"))
    private Role role;
    /** 权限创建人与用户存在多对一关联 */
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="CREATER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_POPEDOM_CREATER"))
    private User creater;
    @Column(name="CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    /* getter and setter method */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Module getModule() {
        return module;
    }
    public void setModule(Module module) {
        this.module = module;
    }
    public Module getOpera() {
        return opera;
    }
    public void setOpera(Module opera) {
        this.opera = opera;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
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
    
}

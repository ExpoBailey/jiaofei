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
 *Role.java 
 *@intention 
 * <p> 角色实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_ID_ROLE")
public class Role implements Serializable{
    private static final long serialVersionUID = 5698207441687246967L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name="ID")
    private Long id;
    @Column(name="NAME", length=50)
    private String name;
    @Column(name="REMARK", length=500)
    private String remark;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="CREATER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_ROLE_CREATER"))
    private User creater;
    @Column(name="CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="MODIFIER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_ROLE_MODIFIER"))
    private User modifier;
    @Column(name="MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    
    @ManyToMany(fetch=FetchType.LAZY, targetEntity=User.class)
    // 生成中间表
    @JoinTable(name="EPDT_ID_USER_ROLE",
                joinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ID"),
                inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))
    private Set<User> users = new HashSet<User>();
    /* getter and setter method */
    public Long getId() {
        return id;
    }
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

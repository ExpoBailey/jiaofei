package com.scarlett.expenditure.admin.identity.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *User.java 
 *@intention 
 * <p> 用户实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Entity @Table(name="EPDT_ID_USER")
public class User implements Serializable{
    private static final long serialVersionUID = 5698207441687246967L;
    
    @Id @Column(name="USER_ID", length=50)
    private String userId;
    @Column(name="PASSWORD", length=50)
    private String password;
    @Column(name="NAME", length=50)
    private String name;
    // 1：男      2：女
    @Column(name="SEX")
    private Short sex = 1;
    @Column(name="EMAIL", length=50)
    private String email;
    @Column(name="TEL", length=50)
    private String tel;
    @Column(name="PHONE", length=50)
    private String phone;
    @Column(name="QQ_NUM", length=50)
    private String qqNum;
    @Column(name="QUESTION")
    private Short question;
    @Column(name="ANSWER", length=200)
    private String answer;
    // 0:新建     1：审核    2：不通过   3：冻结
    @Column(name="STATUS")
    private Short status;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="CREATER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_USER_CREATER"))
    private User creater;
    @Column(name="CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="MODIFIER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_USER_MODIFIER"))
    private User modifier;
    @Column(name="MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="CHECKER", referencedColumnName="USER_ID",
                foreignKey=@ForeignKey(name="FK_USER_CHECKER"))
    private User checker;
    @Column(name="CHECK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkDate;
    /* 用户与角色关系：多对多      懒加载     维护权交给角色类中的users属性 */
    @ManyToMany(fetch=FetchType.LAZY, mappedBy="users", targetEntity=Role.class)
    private Set<Role> roles = new HashSet<Role>();
    
    /* getter and setter method */
    public String getUserId() {
        return userId;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Short getSex() {
        return sex;
    }
    public void setSex(Short sex) {
        this.sex = sex;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getQqNum() {
        return qqNum;
    }
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }
    public Short getQuestion() {
        return question;
    }
    public void setQuestion(Short question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;

        User user = (User) o;

        return userId.equals(user.userId);

    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}

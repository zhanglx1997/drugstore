package com.drugstore.po;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user", schema = "drugstore")
public class UserPO {

    private String id;

    @NotBlank(message = "昵称不能为空！")
    private String nickname;

    private String email;

    @NotBlank(message = "电话不能为空！")
    private String phone;

    @NotBlank(message = "密码不能为空！")
    private String password;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPO userPO = (UserPO) o;

        if (id != null ? !id.equals(userPO.id) : userPO.id != null) return false;
        if (nickname != null ? !nickname.equals(userPO.nickname) : userPO.nickname != null) return false;
        if (email != null ? !email.equals(userPO.email) : userPO.email != null) return false;
        if (phone != null ? !phone.equals(userPO.phone) : userPO.phone != null) return false;
        if (password != null ? !password.equals(userPO.password) : userPO.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}

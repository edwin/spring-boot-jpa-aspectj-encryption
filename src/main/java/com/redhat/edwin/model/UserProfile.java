package com.redhat.edwin.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *     com.redhat.edwin.model.UserProfile
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 04 Mei 2020 11:46
 */
@Entity
@Table(name = "t_user_profile")
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullname;
    private String address;
    private String phonenumber;
    private Date createdDate;

    public UserProfile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

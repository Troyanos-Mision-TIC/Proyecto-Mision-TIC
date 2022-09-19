package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "image")
    private String image;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private Empleado user;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updateAt")
    private Date updateAt;

    public Integer getId() {
        return id;
    }

    public Profile() {
    }

    public Profile(Integer id, String image, String phone, Empleado user, Date createdAt, Date updateAt) {
        this.id = id;
        this.image = image;
        this.phone = phone;
        this.user = user;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Empleado getUser() {
        return user;
    }

    public void setUser(Empleado user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}

package com.example.demo.model;

import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.PsqlEnum;
import com.example.demo.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "profiles")
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "pgsql_enum", typeClass = PsqlEnum.class)
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "auth0id", nullable = false, unique = true)
    private String auth0Id;

    @Column(name = "image")
    private String image;

    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private Employee user;

    @org.hibernate.annotations.Type(type = "pgsql_enum")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    @Column(name = "createdAt")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private Date updatedAt;

    public Profile() {}

    public Profile(String email, String auth0Id, String image, String phone, Employee user) {
        this.email = email;
        this.auth0Id = auth0Id;
        this.image = image;
        this.phone = phone;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAuth0Id() {
        return auth0Id;
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

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdateAt() {
        return updatedAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updatedAt = updateAt;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

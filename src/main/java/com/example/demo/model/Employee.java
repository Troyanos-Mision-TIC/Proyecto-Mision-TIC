package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.PsqlEnum;
import com.example.demo.Role;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@TypeDef(name = "pgsql_enum", typeClass = PsqlEnum.class)
@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnoreProperties(value = {"employees"})
    @ManyToOne
    @JoinColumn(name = "enterprise", nullable = false)
    private Enterprise enterprise;
    
    @org.hibernate.annotations.Type(type = "pgsql_enum")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnoreProperties(value = {"user"})
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Transaction> transactions;

    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Employee() {}

    public Employee(String email, Enterprise enterprise, Role role, Profile profile) {
        this.email = email;
        this.profile = profile;
        this.role = role;
        this.enterprise = enterprise;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Enterprise getEnterprise() {
        return enterprise;
    }
    
    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    /* public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    } */

    /* public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    } */

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", address='" + email + '\'' +
                ", profile=" + profile +
                ", role=" + role +
                ", enterprise=" + enterprise +
                ", transactions=" + transactions +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}

/*package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="lastName")
    private String lastName;

    @Column(name="IdentificationNumber")
    private long IdentificationNumber;

    public User(Integer id, String name, String lastName, long identificationNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        IdentificationNumber = identificationNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getIdentificationNumber() {
        return IdentificationNumber;
    }

    public void setIdentificationNumber(long identificationNumber) {
        IdentificationNumber = identificationNumber;
    }
}
*/
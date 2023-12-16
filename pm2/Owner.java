package com.hachem.pm2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "Owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerID;

    @Column
    private String fullName;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private String phone;

    // Getters and Setters

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /* RELATIONSHIPS */

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "owner_complex", // Name of the join table
            joinColumns = @JoinColumn(name = "ownerID"), // Column for Owner
            inverseJoinColumns = @JoinColumn(name = "complexID") // Column for Complex
    )
    private List<Complex> complexes;

    public List<Complex> getComplexes() {
        return complexes;
    }

    public List<Integer> getComplexIDs() {
        List<Integer> allIDs = new ArrayList<Integer>();

        for (Complex c : complexes) {
            allIDs.add(c.getComplexID());
        }
        return allIDs;

    }

    public void setComplexes(List<Complex> complexes) {
        this.complexes = complexes;
    }

}

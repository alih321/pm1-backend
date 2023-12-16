package com.hachem.pm2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "Apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer aptID;

    @Column
    private String unitCode;

    @Column
    private int numOfBeds;

    @Column
    private int numOfBaths;

    @Column
    private int rent;

    public Integer getAptID() {
        return aptID;
    }

    public void setAptID(Integer aptID) {
        this.aptID = aptID;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public int getNumOfBaths() {
        return numOfBaths;
    }

    public void setNumOfBaths(int numOfBaths) {
        this.numOfBaths = numOfBaths;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getAddress() {

        return complex.getAddress() + " -- Unit #" + this.unitCode;
    }

    /*
     * RELATIONSHIPS
     */

    @ManyToOne
    @JoinColumn(name = "complexID")
    private Complex complex;

    public int getComplexID() {
        return complex.getComplexID();
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tenant> tenants;

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

}

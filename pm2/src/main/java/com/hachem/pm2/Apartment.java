package com.hachem.pm2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "Apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer aptID;

    @Column
    private String UnitCode;

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

    // Getter and Setter for name
    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String UnitCode) {
        this.UnitCode = UnitCode;
    }

    // Getter and Setter for numOfBeds
    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    // Getter and Setter for numOfBaths
    public int getNumOfBaths() {
        return numOfBaths;
    }

    public void setNumOfBaths(int numOfBaths) {
        this.numOfBaths = numOfBaths;
    }

    // Getter and Setter for rent
    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    /*
     * RELATIONSHIPS
     */

    @ManyToOne
    @JoinColumn(name = "complexID") // This column will be added to the 'apartment' table.
    private Complex complex;

    // Getter and Setter for complex
    public int getComplexID() {
        return complex.getComplexID();
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

}

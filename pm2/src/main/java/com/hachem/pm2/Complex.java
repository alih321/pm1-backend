package com.hachem.pm2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import jakarta.persistence.Column;

@Entity
@Table(name="Complex")
public class Complex {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer complexID;

    @Column
    private String name;

    @Column
    private int streetNumber;
    @Column
    private String streetName;
    @Column
    private String city;
    @Column
    private String state;

    public void setID(Integer id) {
        this.complexID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getComplexID() {
        return complexID;
    }

    public String getName() {
        return name;
    }

    // Getter and Setter for streetNumber
    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    // Getter and Setter for streetName
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    // Getter and Setter for city
    public String getCity() {
        return city;
    }

    public String getAddress() {
        return streetNumber + " " + streetName + ", " + city + " " + state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter for state
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @OneToMany(mappedBy = "complex")
    private List<Apartment> apartments;

    // Getter and Setter for apartments
    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }


}
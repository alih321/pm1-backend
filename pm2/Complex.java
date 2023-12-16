//./mvnw spring-boot:run

package com.hachem.pm2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "Complex")
public class Complex {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return streetNumber + " " + streetName + ", " + city + " " + state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /* RELATIONSHIPS */

    @OneToMany(mappedBy = "complex", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apartment> apartments;

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    @ManyToMany(mappedBy = "complexes", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Owner> owners;

    public List<Integer> getOwnerIDs() {
        List<Integer> allIDs = new ArrayList<Integer>();

        for (Owner o : owners) {
            allIDs.add(o.getOwnerID());
        }
        return allIDs;
    }

    public List<String> getOwnerNames() {
        List<String> allNames = new ArrayList<String>();

        for (Owner o : owners) {
            allNames.add(o.getFullName());
        }
        return allNames;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

}
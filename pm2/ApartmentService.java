package com.hachem.pm2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ComplexRepository complexRepository;

    public String addNewApartment(String unitCode, int numOfBeds, int numOfBaths, int rent, int complexID) {
        Optional<Complex> complexOpt = complexRepository.findById(complexID);
        if (!complexOpt.isPresent()) {
            return "Error: Complex with ID " + complexID + " does not exist.";
        }

        Apartment n = new Apartment();
        n.setUnitCode(unitCode);
        n.setNumOfBeds(numOfBeds);
        n.setNumOfBaths(numOfBaths);
        n.setRent(rent);
        n.setComplex(complexOpt.get());

        apartmentRepository.save(n);
        return "APT SAVED.";
    }

    public String deleteApartmentById(int id) {
        try {
            apartmentRepository.deleteById(id);
            return "Delete Successful.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Transactional
    public Iterable<Apartment> findAllApartments() {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> findApartmentById(int id) {
        return apartmentRepository.findById(id);
    }

    public String updateApartment(Integer id, Apartment updatedApartment) {
        Optional<Apartment> existingApartment = apartmentRepository.findById(id);
        if (existingApartment.isPresent()) {
            System.out.println(updatedApartment.getUnitCode());
            Apartment apartment = existingApartment.get();

            // Update fields
            apartment.setUnitCode(updatedApartment.getUnitCode());
            apartment.setNumOfBeds(updatedApartment.getNumOfBeds());
            apartment.setNumOfBaths(updatedApartment.getNumOfBaths());
            apartment.setRent(updatedApartment.getRent());

            apartmentRepository.save(apartment);
            return "Apartment with ID " + id + " Updated.";
        } else {
            return "Apartment with ID " + id + " Not Found.";
        }
    }

}

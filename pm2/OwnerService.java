package com.hachem.pm2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ComplexRepository complexRepository;

    public String addNewOwner(String fullName, String email, String phone, String address, List<Integer> complexIDs) {

        List<Complex> allComplexes = new ArrayList<Complex>();

        if (complexIDs.size() < 1) {
            allComplexes = null;
        } else {
            for (int ID : complexIDs) {
                Optional<Complex> c = complexRepository.findById(ID);
                if (c != null) {
                    allComplexes.add(c.get());
                } else {
                    return "Error: Complex ID: " + ID + " does not exist.";
                }
            }
        }

        Owner owner = new Owner();
        owner.setFullName(fullName);
        owner.setEmail(email);
        owner.setPhone(phone);
        owner.setAddress(address);
        owner.setComplexes(allComplexes);

        ownerRepository.save(owner);
        return "Owner added successfully.";
    }

    public String deleteOwner(int id) {
        ownerRepository.deleteById(id);
        return "Deleted Owner with ID " + id;
    }

    @Transactional
    public Iterable<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> queryOwnerById(int id) {
        return ownerRepository.findById(id);
    }

    public String updateOwner(Integer id, Owner updatedOwner) {
        Optional<Owner> existingOwner = ownerRepository.findById(id);
        if (existingOwner.isPresent()) {
            Owner owner = existingOwner.get();

            // Update fields
            owner.setFullName(updatedOwner.getFullName());
            owner.setEmail(updatedOwner.getEmail());
            owner.setPhone(updatedOwner.getPhone());
            owner.setAddress(updatedOwner.getAddress());

            if (updatedOwner.getComplexes() != null) {
                List<Complex> allComplexes = updatedOwner.getComplexes();
                List<Complex> complexesToSet = new ArrayList<>();
                for (Complex comp : allComplexes) {
                    int compID = comp.getComplexID();
                    Optional<Complex> compEnt = complexRepository.findById(compID);
                    if (compEnt.isPresent()) {
                        complexesToSet.add(compEnt.get());
                    }
                }
                owner.setComplexes(complexesToSet);
            } else {
                owner.setComplexes(new ArrayList<>());
            }

            ownerRepository.save(owner);
            return "Owner with ID " + id + " Updated.";
        } else {
            return "Owner with ID " + id + " Not Found.";
        }
    }

}

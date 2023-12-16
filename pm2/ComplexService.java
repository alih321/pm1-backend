package com.hachem.pm2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplexService {

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public String addNewComplex(Complex complex) {
        complexRepository.save(complex);
        return "Saved. " + complex.getName() + " has been added to the DB.";
    }

    public String deleteComplex(int id) {
        Optional<Complex> c = complexRepository.findById(id);

        if (c.isPresent()) {
            Complex defC = c.get();
            for (int oid : defC.getOwnerIDs()) {
                Optional<Owner> o = ownerRepository.findById(oid);
                if (o.isPresent()) {
                    Owner defO = o.get();
                    defO.getComplexes().remove(defC);
                }
            }
        }

        complexRepository.deleteById(id);
        return "Complex with ID " + id + " Deleted.";
    }

    public String deleteAllComplexes() {
        complexRepository.deleteAll();
        return "ALL COMPLEXES DELETED.";
    }

    public Iterable<Complex> getAllComplexes() {
        return complexRepository.findAll();
    }

    public Iterable<Complex> getComplexByName(String name) {
        return complexRepository.findByName(name);
    }

    public Optional<Complex> getComplexById(int id) {
        return complexRepository.findById(id);
    }

    public String updateComplex(Integer id, Complex updatedComplex) {
        Optional<Complex> existingComplex = complexRepository.findById(id);
        if (existingComplex.isPresent()) {
            Complex complex = existingComplex.get();
            // Update fields
            complex.setName(updatedComplex.getName());
            complex.setStreetNumber(updatedComplex.getStreetNumber());
            complex.setStreetName(updatedComplex.getStreetName());
            complex.setCity(updatedComplex.getCity());
            complex.setState(updatedComplex.getState());

            complexRepository.save(complex);
            return "Complex with ID " + id + " Updated.";
        } else {
            return "Complex with ID " + id + " Not Found.";
        }
    }

}

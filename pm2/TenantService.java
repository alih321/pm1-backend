package com.hachem.pm2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;

    public String addNewTenant(String fullName, int creditScore, int salary, String email, String phone, int aptID) {
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(aptID);
        if (!apartmentOpt.isPresent()) {
            return "Error: Apartment with ID " + aptID + " does not exist.";
        }

        Tenant tenant = new Tenant();
        tenant.setFullName(fullName);
        tenant.setCreditScore(creditScore);
        tenant.setSalary(salary);
        tenant.setApartment(apartmentOpt.get());
        tenant.setEmail(email);
        tenant.setPhone(phone);

        tenantRepository.save(tenant);
        return "Tenant added successfully.";
    }

    public String deleteTenant(int id) {
        tenantRepository.deleteById(id);
        return "Deleted Tenant with ID " + id;
    }

    @Transactional
    public Iterable<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Iterable<Tenant> queryTenantByName(String name) {
        return tenantRepository.findByFullName(name);
    }

    public Optional<Tenant> queryTenantById(int id) {
        return tenantRepository.findById(id);
    }

    public String updateTenant(Integer id, Tenant updatedTenant) {
        Optional<Tenant> existingTenant = tenantRepository.findById(id);
        if (existingTenant.isPresent()) {
            Tenant tenant = existingTenant.get();

            // Update fields
            tenant.setFullName(updatedTenant.getFullName());
            tenant.setCreditScore(updatedTenant.getCreditScore());
            tenant.setSalary(updatedTenant.getSalary());
            tenant.setEmail(updatedTenant.getEmail());
            tenant.setPhone(updatedTenant.getPhone());

            tenantRepository.save(tenant);
            return "Tenant with ID " + id + " Updated.";
        } else {
            return "Tenant with ID " + id + " Not Found.";
        }
    }
}

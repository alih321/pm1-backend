package com.hachem.pm2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {

    public List<Tenant> findByFullName(String fullName);

}
package com.hachem.pm2;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ComplexRepository extends CrudRepository<Complex, Integer>
{
    
    public List<Complex> getByName(String name);

}
package com.hachem.pm2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ApartmentRepository extends CrudRepository<Apartment, Integer>
{

    @Query("SELECT m FROM Apartment m WHERE :rentVal > m.rent")
    List<Apartment> findByRentLessThan(@Param("rentVal") int rentVal);
    
}
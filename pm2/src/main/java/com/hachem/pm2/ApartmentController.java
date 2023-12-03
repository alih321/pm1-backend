package com.hachem.pm2;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/apartments") // This means URL's start with /demo (after Application path)
public class ApartmentController {

  @Autowired
  private ApartmentRepository apartmentRepository;

  /*
   *
   * APARTMENT END POINTS
   *
   */

  @PostMapping(path = "/add")
  public @ResponseBody String addNewApartment(
      @RequestParam String UnitCode,
      @RequestParam int numofBeds,
      @RequestParam int numofBaths,
      @RequestParam int rent) {

    Apartment n = new Apartment();

    n.setUnitCode(UnitCode);
    n.setNumOfBeds(numofBeds);
    n.setNumOfBaths(numofBaths);
    n.setRent(rent);

    apartmentRepository.save(n);

    return "APT SAVED.";
  }

  @GetMapping(path = "query/max_rent={mRent}")
  public @ResponseBody Iterable<Apartment> queryComplex(@PathVariable int mRent) {

    return apartmentRepository.findByRentLessThan(mRent);
  }

}
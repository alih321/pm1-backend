package com.hachem.pm2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/apartments") // This means URL's start with /demo (after Application path)
public class ApartmentController {

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Autowired
  private ComplexRepository complexRepository;

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
      @RequestParam int rent,
      @RequestParam int complexID) {

    // Retrieve the Complex entity
    Optional<Complex> complexOpt = complexRepository.findById(complexID);

    if (!complexOpt.isPresent()) {
      return "Error: Complex with ID " + complexID + " does not exist.";
    }

    Apartment n = new Apartment();

    n.setUnitCode(UnitCode);
    n.setNumOfBeds(numofBeds);
    n.setNumOfBaths(numofBaths);
    n.setRent(rent);
    n.setComplex(complexOpt.get());

    apartmentRepository.save(n);

    return "APT SAVED.";
  }

  @PostMapping(path = "delete/{id}")
  public @ResponseBody String deleteApartmentById(@PathVariable int id) {

    try {
      apartmentRepository.deleteById(id);
      return "Delete Successful.";
    } catch (Exception e) {
      return "Error: " + e.getMessage();
    }
  }

  // @PostMapping(path = "/add")
  // public @ResponseBody String addApartment(@RequestBody Apartment apartment) {

  // apartmentRepository.save(apartment);
  // return "Apartment Saved.";
  // }

  @GetMapping(path = "query/all")
  public @ResponseBody Iterable<Apartment> queryAllApartments() {

    return apartmentRepository.findAll();
  }

  @GetMapping(path = "query/max_rent={mRent}")
  public @ResponseBody Iterable<Apartment> queryComplex(@PathVariable int mRent) {

    return apartmentRepository.findByRentLessThan(mRent);
  }

}
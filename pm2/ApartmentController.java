package com.hachem.pm2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(path = "/apartments")
public class ApartmentController {

  @Autowired
  private ApartmentService apartmentService;

  /*
   *
   * APARTMENT END POINTS
   *
   */

  @PostMapping(path = "/add")
  public @ResponseBody String addNewApartment(
      @RequestParam String unitCode,
      @RequestParam int numOfBeds,
      @RequestParam int numOfBaths,
      @RequestParam int rent,
      @RequestParam int complexID) {

    return apartmentService.addNewApartment(unitCode, numOfBeds, numOfBaths, rent, complexID);

  }

  @PostMapping(path = "delete/id={id}")
  public @ResponseBody String deleteApartmentById(@PathVariable int id) {

    return apartmentService.deleteApartmentById(id);
  }

  @GetMapping(path = "query/all")
  public @ResponseBody Iterable<Apartment> queryAllApartments() {

    return apartmentService.findAllApartments();
  }

  @GetMapping(path = "query/id={id}")
  public @ResponseBody Optional<Apartment> queryApartmentById(@PathVariable int id) {
    return apartmentService.findApartmentById(id);
  }

  @PutMapping(path = "/update/id={ID}")
  public @ResponseBody String updateApartment(@PathVariable int ID, @RequestBody Apartment apartment) {
    return apartmentService.updateApartment(ID, apartment);
  }

}
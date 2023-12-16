package com.hachem.pm2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(path = "/complexes")
@CrossOrigin
public class ComplexController {

  @Autowired
  private ComplexService complexService;

  /*
   * COMPLEX END POINTS
   */

  @PostMapping(path = "/add")
  public @ResponseBody String addNewComplex(@RequestBody Complex complex) {

    return complexService.addNewComplex(complex);
  }

  @PostMapping(path = "/delete")
  public @ResponseBody String deleteComplex(@RequestParam int ID) {

    return complexService.deleteComplex(ID);

  }

  @PostMapping(path = "/delete/all")
  public @ResponseBody String deleteAllComplex() {

    return complexService.deleteAllComplexes();
  }

  @GetMapping(path = "query/all")
  public @ResponseBody Iterable<Complex> getAllComplex() {

    return complexService.getAllComplexes();
  }

  @GetMapping(path = "/query/name={name}")
  public @ResponseBody Iterable<Complex> queryComplexByName(@PathVariable String name) {

    return complexService.getComplexByName(name);
  }

  @GetMapping(path = "/query/id={id}")
  public @ResponseBody Optional<Complex> queryComplexById(@PathVariable int id) {

    return complexService.getComplexById(id);
  }

  @PutMapping(path = "/update/id={ID}")
  public @ResponseBody String updateComplex(@PathVariable int ID, @RequestBody Complex complex) {
    return complexService.updateComplex(ID, complex);
  }

}
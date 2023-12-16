package com.hachem.pm2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(path = "/owners")
@CrossOrigin
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /*
     * OWNER END POINTS
     */

    @PostMapping(path = "/add")
    public @ResponseBody String addNewOwner(@RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam List<Integer> complexIDs) {
        return ownerService.addNewOwner(fullName, email, phone, address, complexIDs);
    }

    @PostMapping(path = "/delete/id={id}")
    public @ResponseBody String deleteOwner(@PathVariable int id) {
        return ownerService.deleteOwner(id);
    }

    @GetMapping(path = "/query/all")
    public @ResponseBody Iterable<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping(path = "/query/id={id}")
    public @ResponseBody Optional<Owner> queryOwnerById(@PathVariable int id) {
        return ownerService.queryOwnerById(id);
    }

    @PutMapping(path = "/update/id={ID}")
    public @ResponseBody String updateOwner(@PathVariable int ID, @RequestBody Owner owner) {
        return ownerService.updateOwner(ID, owner);
    }

}
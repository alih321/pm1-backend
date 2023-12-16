package com.hachem.pm2;

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
@RequestMapping(path = "/tenants")
@CrossOrigin
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /*
     * TENANT END POINTS
     */

    @PostMapping(path = "/add")
    public @ResponseBody String addNewTenant(@RequestParam String fullName, @RequestParam int creditScore,
            @RequestParam int salary, @RequestParam String email, @RequestParam String phone, @RequestParam int aptID) {
        return tenantService.addNewTenant(fullName, creditScore, salary, email, phone, aptID);
    }

    @PostMapping(path = "/delete/id={id}")
    public @ResponseBody String deleteTenant(@PathVariable int id) {
        return tenantService.deleteTenant(id);
    }

    @GetMapping(path = "/query/all")
    public @ResponseBody Iterable<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping(path = "/query/name={name}")
    public @ResponseBody Iterable<Tenant> queryTenantByName(@PathVariable String name) {
        return tenantService.queryTenantByName(name);
    }

    @GetMapping(path = "/query/id={id}")
    public @ResponseBody Optional<Tenant> queryTenantById(@PathVariable int id) {
        return tenantService.queryTenantById(id);
    }

    @PutMapping(path = "/update/id={ID}")
    public @ResponseBody String updateTenant(@PathVariable int ID, @RequestBody Tenant tenant) {
        return tenantService.updateTenant(ID, tenant);
    }

}
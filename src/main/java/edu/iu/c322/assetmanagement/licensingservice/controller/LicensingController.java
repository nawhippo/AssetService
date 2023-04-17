package edu.iu.c322.assetmanagement.licensingservice.controller;

import edu.iu.c322.assetmanagement.licensingservice.model.License;
import edu.iu.c322.assetmanagement.licensingservice.repository.LicenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/licensings")
public class LicensingController {
    private LicenseRepository repository;

    public LicensingController(LicenseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<License> getLicensings(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<License> getLicensing(@PathVariable int id){
        return repository.findById(id);
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@RequestBody License license){
        License addedLicense = repository.save(license);
        return addedLicense.getId();
    }

}

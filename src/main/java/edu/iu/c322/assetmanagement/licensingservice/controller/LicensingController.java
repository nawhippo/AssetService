package edu.iu.c322.assetmanagement.licensingservice.controller;

import edu.iu.c322.assetmanagement.licensingservice.client.OrganizationClient;
import edu.iu.c322.assetmanagement.licensingservice.model.License;
import edu.iu.c322.assetmanagement.licensingservice.model.Organization;
import edu.iu.c322.assetmanagement.licensingservice.repository.LicenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/licensings")
public class LicensingController {
    private LicenseRepository repository;

    private OrganizationClient organizationClient;

    public LicensingController(LicenseRepository repository, OrganizationClient organizationClient) {
        this.repository = repository;
        this.organizationClient = organizationClient;
    }

    @GetMapping
    public List<License> getLicensings(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public License getLicensing(@PathVariable int id){

        Optional<License> maybeLicense = repository.findById(id);
        if(maybeLicense.isPresent()){
            License license = maybeLicense.get();
            Optional<Organization> maybeOrganization = organizationClient
                    .getOrganization(license.getOrganizationId());
            if(maybeOrganization.isPresent()){
                Organization organization = maybeOrganization.get();
                license.setOrganization(organization);
                return license;
            }
        } else {
            throw new IllegalStateException("licensing id is invalid.");
        }
        return null;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@RequestBody License license){
        License addedLicense = repository.save(license);
        return addedLicense.getId();
    }

}

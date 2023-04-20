package edu.iu.c322.assetmanagement.organizationservice.controller;

import edu.iu.c322.assetmanagement.organizationservice.model.Organization;
import edu.iu.c322.assetmanagement.organizationservice.repository.OrganizationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private OrganizationRepository repository;

    public OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Organization> getOrganizations(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Organization> getOrganization(@PathVariable int id){
           return repository.findById(id);
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@RequestBody Organization organization){
        Organization addedOrganization = repository.save(organization);
        return addedOrganization.getId();
    }

    @PutMapping
    public void update(@RequestBody Organization organization){
        Organization addedOrganization = repository.save(organization);
    }
}

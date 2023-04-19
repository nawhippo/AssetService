package edu.iu.c322.assetmanagement.licensingservice.controller;

import edu.iu.c322.assetmanagement.licensingservice.client.LicenseClient;
import edu.iu.c322.assetmanagement.licensingservice.client.OrganizationClient;
import edu.iu.c322.assetmanagement.licensingservice.model.Asset;
import edu.iu.c322.assetmanagement.licensingservice.repository.AssetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/licensings")
public class AssetController {
    private AssetRepository repository;

    private LicenseClient licenseClient;

    public AssetController(AssetRepository repository, LicenseClient licenseClient) {
        this.repository = repository;
        this.licenseClient = licenseClient;
    }

    @GetMapping("/assets")
    public List<Asset> getAssets(){
        return repository.findAll();
    }

    @GetMapping("/assets/{id}")
    public Asset getAsset(@PathVariable int id){

        Optional<Asset> maybeAsset = repository.findById(id);
        if(maybeAsset.isPresent()){
            Asset asset = maybeAsset.get();
            Optional<License> maybeLicense = licenseClient
                    .getLicense(asset.getLicenseId());
            if(maybeAsset.isPresent()){
                License license = maybeLicense.get();
                asset.setLicense(license);
                return asset;
            }
        } else {
            throw new IllegalStateException("Asset id is invalid.");
        }
        return null;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/assets")
    public int create(@RequestBody Asset asset){
        Asset addedAsset = repository.save(asset);
        return addedAsset.getId();
    }

}

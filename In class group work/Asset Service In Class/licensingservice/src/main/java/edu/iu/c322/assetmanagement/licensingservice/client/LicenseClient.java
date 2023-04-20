package edu.iu.c322.assetmanagement.licensingservice.client;

import edu.iu.c322.assetmanagement.licensingservice.model.Organization;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class LicenseClient {
    private RestTemplate restTemplate;

    public LicenseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<License> getLicense(int licenseId){
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://organization-service/Licensing/{LicenseId}",
                        HttpMethod.GET,
                        null, License.class, licenseId);

        return Optional.ofNullable(restExchange.getBody());
    }
}

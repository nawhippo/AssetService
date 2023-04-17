package edu.iu.c322.assetmanagement.licensingservice.repository;

import edu.iu.c322.assetmanagement.licensingservice.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Integer> {
}

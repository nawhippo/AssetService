package edu.iu.c322.assetmanagement.licensingservice.repository;

import edu.iu.c322.assetmanagement.licensingservice.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
}

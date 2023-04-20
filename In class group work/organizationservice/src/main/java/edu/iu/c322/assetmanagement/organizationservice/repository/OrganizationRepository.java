package edu.iu.c322.assetmanagement.organizationservice.repository;

import edu.iu.c322.assetmanagement.organizationservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{

}

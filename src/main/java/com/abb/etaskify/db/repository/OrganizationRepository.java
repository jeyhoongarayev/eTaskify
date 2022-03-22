package com.abb.etaskify.db.repository;

import com.abb.etaskify.db.entity.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, String> {

    Organization findByOrganizationNameAndIsActive(String organizationName, Boolean isActive);
    Organization findByEmailAndIsActive(String email, Boolean isActive);
    Optional<Organization> findByIdAndIsActive(String organizationId, Boolean isActive);
}

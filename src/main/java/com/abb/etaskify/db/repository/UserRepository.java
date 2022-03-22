package com.abb.etaskify.db.repository;

import com.abb.etaskify.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByIdOrganizationAndIsActive(String idOrganization, Boolean isActive);

    Optional<User> findByEmailAndIsActive(String email, Boolean isActive);

    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndIsActive(String userId, Boolean IsActive);

}

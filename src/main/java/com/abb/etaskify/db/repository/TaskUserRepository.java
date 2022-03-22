package com.abb.etaskify.db.repository;

import com.abb.etaskify.db.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskUserRepository extends JpaRepository<TaskUser,String> {

}

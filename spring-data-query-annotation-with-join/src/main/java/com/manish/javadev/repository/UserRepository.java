package com.manish.javadev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.manish.javadev.app.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	String JOIN_QURY = "Select u.USER_NAME, r.ROLE from TBL_USER u JOIN TBL_ROLE r on u.USER_ID=r.USER_ID_FK";

	@Query(value = JOIN_QURY, nativeQuery = true)
	List<String[]> getAllRecord();
}
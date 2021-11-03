package com.padillatomas.icons.icons.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padillatomas.icons.icons.auth.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

		UserEntity findByUsername(String username);
}

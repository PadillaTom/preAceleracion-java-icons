package com.padillatomas.icons.icons.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padillatomas.icons.icons.entity.IconEntity;

@Repository
public interface IconRepository extends JpaRepository<IconEntity, Long> {
	
	// Method to pass Specification;
	List<IconEntity> findAll(Specification<IconEntity> spec);

}

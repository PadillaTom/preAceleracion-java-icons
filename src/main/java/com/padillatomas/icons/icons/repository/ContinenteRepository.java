package com.padillatomas.icons.icons.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padillatomas.icons.icons.entity.ContinenteEntity;

@Repository
public interface ContinenteRepository extends JpaRepository<ContinenteEntity,Long> {

}

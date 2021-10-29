package com.padillatomas.icons.icons.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.padillatomas.icons.icons.dto.PaisFiltersDTO;
import com.padillatomas.icons.icons.entity.PaisEntity;

@Component
public class PaisSpecification {
	
	public Specification<PaisEntity> getByFilters(PaisFiltersDTO filtersDTO){
		
		return (root, query, criteriaBuilder)-> {
			List<Predicate> predicates = new ArrayList<>();
			
			//
			// Name:
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
									criteriaBuilder.lower(root.get("denominacion")),
									"%" + filtersDTO.getName().toLowerCase() + "%")
						);				
			}
			
			//
			// Continent:
			if(StringUtils.hasLength(filtersDTO.getContinent())) {
				Long myId = Long.parseLong(filtersDTO.getContinent());
				System.out.println(myId);
				predicates.add(
						criteriaBuilder.equal(root.get("continenteId"), myId)
						);						
			}
			
			query.distinct(true);
			
			//
			// Filter por ORDEN:
			String orderByField = "denominacion";
			query.orderBy(
						filtersDTO.isASC() ?
								criteriaBuilder.asc(root.get(orderByField))
								:
								criteriaBuilder.desc(root.get(orderByField))									
					);
			
			// Main Return: Generamos el Predicado como LISTA.
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
	}
}

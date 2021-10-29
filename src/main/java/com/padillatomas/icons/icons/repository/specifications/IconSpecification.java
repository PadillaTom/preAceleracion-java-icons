package com.padillatomas.icons.icons.repository.specifications;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.padillatomas.icons.icons.dto.IconFiltersDTO;
import com.padillatomas.icons.icons.entity.IconEntity;
import com.padillatomas.icons.icons.entity.PaisEntity;

@Component
public class IconSpecification {

	public Specification<IconEntity> getByFilters(IconFiltersDTO filtersDTO){
		
		// Main Return: LAMBDA
		return (root, query, criteriaBuilder) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			// ****
			// Preguntaremos por cada uno de los FiltersDTOS
			// IF: getNOMBRE_ATRIBUTO() hasLength, para saber si EXISTE.
			// IF TRUE -> 
			//		Agregaremos a Predicates: El elemento que MATCHEE
			//		su PARAMETER con el "denominacion" en la Database.			
			// ****			
			
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
			// Date:
			if(StringUtils.hasLength(filtersDTO.getDate())) {
				
				// String to LocalDate:
				String dateToParse = filtersDTO.getDate();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate date = LocalDate.parse(dateToParse, formatter);
							
				predicates.add(
						criteriaBuilder.equal(root.<LocalDate>get("fechaCreacion"),date)
						);
			}
			
			//
			// PaisesIds: NO ESTA VACIO:			
			if(!CollectionUtils.isEmpty(filtersDTO.getPaisesIds())) {
				// Automaticamente usara la tabla Icon_PaisesID para unir
				// Pais que matchee dentro de lista PaisesIds.				
				Join<PaisEntity, IconEntity> toBeJoined = root.join("paises", JoinType.INNER);
				Expression<String> paisesId = toBeJoined.get("id");
				
				predicates.add(paisesId.in(filtersDTO.getPaisesIds()));
			}
			
			// Remove Duplicates: 
			// Ejemplo -> Paises dentro de Icons, Mismo Icon puede pertenecer a varios paises
			// Retornara una sola instancia de dicho Icon.
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

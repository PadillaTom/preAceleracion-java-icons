package com.padillatomas.icons.icons.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IconFiltersDTO {
	
	private String name;
	private String date;
	private List<Long> paisesIds;
	private String order;
	
	public boolean isASC() {
		return order.compareToIgnoreCase("ASC") == 0 ;
	}
	
	public boolean isDESC() {
		return order.compareToIgnoreCase("DESC") == 0 ;
	}

}

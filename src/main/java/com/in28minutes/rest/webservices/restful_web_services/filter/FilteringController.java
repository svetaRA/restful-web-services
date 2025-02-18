package com.in28minutes.rest.webservices.restful_web_services.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);; 
		mappingJacksonValue.setFilters(filters);
		return  mappingJacksonValue;
	}
	
	
		@GetMapping("/filtering-list")
		public MappingJacksonValue filteringList() {
			
			List<SomeBean> list = new ArrayList<>();
			list.add(new SomeBean("v1", "v2", "v3"));
			list.add(new SomeBean("v4", "v5", "v6"));
			
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
			SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
			FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);; 
			mappingJacksonValue.setFilters(filters);
			/*
			 * return Arrays.asList(new SomeBean("value1", "value2", "value3"), (new
			 * SomeBean("value4", "value5", "value6") ));
			 */
			
			return mappingJacksonValue;
			
		}

}

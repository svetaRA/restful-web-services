package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
		
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecodVersionOfPerson() {
		return new PersonV2(new Name("Bob", "Charley"));
		
	}
	
	@GetMapping(path = "/person" , params ="version=1")
	public PersonV1 getFirstVersionOfPersonParamRequest() {
		return new PersonV1("Bob Charlie");
		
	} 
	
	@GetMapping(path = "/person" , params ="version=2")
	public PersonV2 getSecondVersionOfPersonParamRequest() {
		return new PersonV2(new Name("Bob" , "Charlie"));
		
	
	}
	
	@GetMapping(path = "/person/header" , headers ="X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonHeaders() {
		return new PersonV1("Bob Charlie");
		
	} 
	
	@GetMapping(path = "/person/header" , headers ="X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonHeaders() {
		return new PersonV2(new Name("Bob" , "Charlie"));
		
	} 
	
	@GetMapping(path = "/person/accept" , produces ="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfAcceptHeaders() {
		return new PersonV1("Bob Charlie");
		
	} 
	
	@GetMapping(path = "/person/accept" , produces ="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeaders() {
		return new PersonV2(new Name("Bob" , "Charlie"));
		
	} 
	
	
	

}

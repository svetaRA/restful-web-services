package com.in28minutes.rest.webservices.restful_web_services.user;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource  {
	
	private UserDAOService service;
	public UserResource(UserDAOService service) {
		this.service =service;
	}
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return service.findAll();
		}
	
	@GetMapping("/user/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id) {
    	User user = service.findUser(id);
    	
    	if(user == null)
    	 throw new UserNotFoundException("id:" +id);
    	//hateoas
    	EntityModel<User> entityModel =EntityModel.of(user);
    	WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
    	entityModel.add(link.withRel("all-users"));
    	 
    	return entityModel;
    	
    }
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedUser.getId()).
				toUri();
	//location -/users/4
	return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
    	service.deleteById(id);
    	    	    	    	   	
    }
	
	
}

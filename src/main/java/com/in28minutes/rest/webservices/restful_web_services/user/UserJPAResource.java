package com.in28minutes.rest.webservices.restful_web_services.user;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.in28minutes.rest.webservices.restful_web_services.jpa.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource  {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserJPAResource(UserRepository repository, PostRepository postRepository) {
		
		this.userRepository= repository;
		this.postRepository=postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers(){
			return userRepository.findAll();
		}
	
	@GetMapping("/jpa/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);
    	
    	if(user.isEmpty())
    	 throw new UserNotFoundException("id:" +id);
    	//hateoas
    	EntityModel<User> entityModel =EntityModel.of(user.get());
    	WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
    	entityModel.add(link.withRel("all-users"));
    	 
    	return entityModel;
    	
    }
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedUser.getId()).
				toUri();
	//location -/users/4 
	return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePostforUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id"+id);
		}
		
	//location -/users/4 
	return user.get().getPosts();
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id"+id);
		post.setUser(user.get());
		Post savedPost  =postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedPost.getId()).
				toUri(); 
	//location -/users/4 
	return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/jpa/user/{id}")
    public void deleteUser(@PathVariable int id) {
    	userRepository.deleteById(id);
    	    	    	    	   	
    }
	
	
}

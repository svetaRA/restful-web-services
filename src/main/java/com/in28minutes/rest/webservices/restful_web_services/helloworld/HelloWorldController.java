package com.in28minutes.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//
@RestController
class HelloWorldController {
   
	/*
	 * @RequestMapping(method = RequestMethod.GET , path ="/hello-world") public
	 * String helloworld() { return "Hello World"; }
	 */
	private MessageSource messageSource;
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
		
	}
	@GetMapping(path ="/hello-world")
	public String helloworld() {
		return "Hello World";
	} 
	
	@GetMapping(path ="/hello-world-bean")
	public HelloWordBean helloworldBean() {
		return new HelloWordBean("Hello World !!");
	} 
	
	//path Parameters
	// /users/{id}/todos/{id}
	@GetMapping(path ="/hello-world/path-variable/{name}")
	public HelloWordBean helloworldPathVariable(@PathVariable String name) {
		return new HelloWordBean(String.format("Hello World, %s", name));
	} 
	
	@GetMapping(path ="/hello-world-internalized")
	public String helloworldInternalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message",locale);
		//return "Hello World";
	} 
	
}

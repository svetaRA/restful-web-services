/*
 * package com.in28minutes.rest.restful_web_services.security;
 * 
 * import org.springframework.context.annotation.Bean; import static
 * org.springframework.security.config.Customizer.withDefaults; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SpringSecurityConfiguration {
 * 
 * 
 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
 * Exception { //1) All requests are authenticated
 * 
 * 
 * http.authorizeHttpRequests( auth -> auth.anyRequest().authenticated() );
 * 
 * //2) if a request is not authenticated a web page is shown
 * //http.httpBasic(withDefaults());
 * 
 * //3) CSRF ->POST, PUT
 * 
 * //http.csrf(csrf -> csrf.disable()); //http.cors(withDefaults());
 * 
 * http .csrf((csrf) -> csrf .ignoringRequestMatchers("/no-csrf") );
 * 
 * return http.build(); }
 * 
 * }
 */
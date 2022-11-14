package com.college.studentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.college.studentmanagement.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Bean
	protected UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	
    	DaoAuthenticationProvider authenticate = new DaoAuthenticationProvider();
    	authenticate.setUserDetailsService(userDetailsService());
    	authenticate.setPasswordEncoder(passwordEncoder());
    	
    	return authenticate;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	 http.csrf().disable()
    	     .cors().disable()
             .authorizeRequests()
             .antMatchers(HttpMethod.GET,"/student/**").hasAnyRole("USER","ADMIN")
             .antMatchers(HttpMethod.GET,"/user/**").hasAnyRole("ADMIN")
             .antMatchers(HttpMethod.POST,"/student/**").hasAnyRole("ADMIN")
             .antMatchers(HttpMethod.DELETE,"/student/**").hasAnyRole("ADMIN")
             .antMatchers(HttpMethod.PUT,"/student/**").hasAnyRole("ADMIN")
             .anyRequest()
             .authenticated().and().formLogin().defaultSuccessUrl("/student/list", true)
             .and()
             .httpBasic();
    			   
    	  return http.build();
    }
    
}













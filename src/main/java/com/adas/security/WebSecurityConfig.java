//package com.adas.security;

//import javax.sql.DataSource;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
//public class WebSecurityConfig extends WebSecurityConfiguration{
//
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	private BCryptPasswordEncoder passEncoder;
//	
//	@Autowired
//	public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
//		builder.jdbcAuthentication()
//		.dataSource(dataSource)
//		.passwordEncoder(passEncoder)
//		.usersByUsernameQuery("SELECT nombre, contrasenia FROM usuarios WHERE nombre=?")
//		.authoritiesByUsernameQuery("SELECT nombre, cargo FROM usuarios WHERE nombre=?");
//		
//	}
//}

package com.example.ExampleDemo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class JavaSecure {

	
//	@Bean
//	public InMemoryUserDetailsManager configUser() {
//		
//		GrantedAuthority g1 = new SimpleGrantedAuthority("user");
//		
//		List<GrantedAuthority> list = new ArrayList<>();
//		list.add(g1);
//		UserDetails user1 = new User("user1", "password",list );
//		
//		InMemoryUserDetailsManager inuser = new InMemoryUserDetailsManager();
//		inuser.createUser(user1);
//		return inuser;
//	}
	
//	@Bean
//	public PasswordEncoder encode()
//	{
//		return NoOpPasswordEncoder.getInstance();
//	}
	
//	@Bean 
//	public InMemoryUserDetailsManager configpass()
//	{
//		GrantedAuthority auth = new SimpleGrantedAuthority("user");
//		
//		List<GrantedAuthority> listofAuth = new ArrayList<>();
//		listofAuth.add(auth);
//		UserDetails user = new User("user2","password2",listofAuth);
//		InMemoryUserDetailsManager mm = new InMemoryUserDetailsManager();
//		mm.createUser(user);
//		return mm;
//	}
	
	@Bean
	public InMemoryUserDetailsManager configure()
	{
		UserDetails user1 = User.withUsername("user1")
				.password("password1")
				.roles("admin","user")
				.build();
		
		return new InMemoryUserDetailsManager(user1);
	}
}

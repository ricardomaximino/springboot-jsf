package com.brasajava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brasajava.beans.CustomUserDetails;
import com.brasajava.beans.User;
import com.brasajava.repositories.UserRepository;
import com.brasajava.repositories.UserRolesRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, UserRolesRepository userRolesRepository){
		this.userRepository = userRepository;
		this.userRolesRepository = userRolesRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user.equals(null)){
			throw new UsernameNotFoundException("No user present with username: " + username);
		}else{
			List<String> userRoles = userRolesRepository.findRoleByUsername(username);
			return new CustomUserDetails(user,userRoles);
		}
	}

}

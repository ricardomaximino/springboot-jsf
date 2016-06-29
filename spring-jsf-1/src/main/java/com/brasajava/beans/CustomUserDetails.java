package com.brasajava.beans;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class CustomUserDetails extends User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> userRoles;
	public CustomUserDetails(User user,List<String> userRoles){
		System.out.println("USER: " + user);
		System.out.println("ROLES: " + userRoles);
		this.setActive(user.isActive());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.userRoles = userRoles;
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

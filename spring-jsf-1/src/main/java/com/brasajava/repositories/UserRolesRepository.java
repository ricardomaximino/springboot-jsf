package com.brasajava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brasajava.beans.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {
	@Query("select a.role from UserRoles a,User b where b.username=?1 and a.userId = b.id")
	public List<String> findRoleByUsername(String username);

}

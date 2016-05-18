package com.brasajava.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brasajava.beans.User;

public interface UserRepository extends CrudRepository<User, Long> {
	 List<User> findByName(String name);
	 List<User> findByFirstLastName(String firstLastName);
	 List<User> findBySecondLastName(String SecondLastName);
	 User findByEmail(String email);
	 
}

package com.brasajava.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brasajava.beans.CustomerImpl;

public interface CustomerRepository extends CrudRepository<CustomerImpl, Long> {

    List<CustomerImpl> findByLastName(String lastName);
}
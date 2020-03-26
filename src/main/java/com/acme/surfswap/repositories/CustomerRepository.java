package com.acme.surfswap.repositories;

import com.acme.surfswap.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByLastName(String lastName);

    Set<Customer> findAllByLastNameLike(String lastName);
}

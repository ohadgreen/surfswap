package com.acme.surfswap.repositories;

import com.acme.surfswap.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    com.acme.surfswap.model.Owner findByLastName(String lastName);
}

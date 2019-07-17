package com.acme.surfswap.services;

import com.acme.surfswap.model.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastNameLike(String lastName);
}

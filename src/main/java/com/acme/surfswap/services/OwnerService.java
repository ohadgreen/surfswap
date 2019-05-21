package com.acme.surfswap.services;

import com.acme.surfswap.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}

package com.acme.surfswap.repositories;

import com.acme.surfswap.model.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Long> {
    Store findByName(String name);
}

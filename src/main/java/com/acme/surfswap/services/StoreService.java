package com.acme.surfswap.services;

import com.acme.surfswap.model.Store;

public interface StoreService extends CrudService<Store, Long> {
    Store findByName(String name);
}

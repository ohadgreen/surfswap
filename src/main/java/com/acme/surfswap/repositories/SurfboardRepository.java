package com.acme.surfswap.repositories;

import com.acme.surfswap.model.Surfboard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface SurfboardRepository extends CrudRepository<Surfboard, Long> {
    Set<Surfboard> findByOwnerId(long ownerId);
}

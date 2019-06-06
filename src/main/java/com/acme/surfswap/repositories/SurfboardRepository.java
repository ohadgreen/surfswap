package com.acme.surfswap.repositories;

import com.acme.surfswap.model.Surfboard;
import org.springframework.data.repository.CrudRepository;

public interface SurfboardRepository extends CrudRepository<Surfboard, Long> {
}
package com.acme.surfswap.repositories;

import com.acme.surfswap.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
}

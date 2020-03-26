package com.acme.surfswap.repositories;

import com.acme.surfswap.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}

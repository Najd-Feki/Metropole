package Metropole.demo.repositories;


import Metropole.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Reservationrepo extends CrudRepository<Reservation, Integer>, JpaRepository<Reservation,Integer> {

   List<Reservation> findAllByStartDateGreaterThan(LocalDate startDate);
   void deleteByResNr(int resNr);
}

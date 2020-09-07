package Metropole.demo.dao;

import Metropole.demo.model.Employee;
import Metropole.demo.model.Reservation;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Reservationdao {
    List<Reservation> findAll();
    Long count();
    void delete(Reservation entity);
    void deleteAll();
    Optional<Reservation> findById(int id);
    Reservation save(Reservation entity);
    List<Reservation> saveAll(List<Reservation> list);
    List<Reservation> findAllByStartDateGreaterThan(LocalDate startDate);
    void deleteByResNr(int resNr);
}

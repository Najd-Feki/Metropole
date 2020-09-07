package Metropole.demo.service;

import Metropole.demo.dao.Reservationdao;

import Metropole.demo.model.Employee;
import Metropole.demo.model.Reservation;

import Metropole.demo.repositories.Reservationrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Transactional
@Service

public class Reservationservice implements Reservationdao {

    @Autowired
    private Reservationrepo reservationrepo;

    @Override
    public List<Reservation> findAll() {
        return (List<Reservation>) reservationrepo.findAll();
    }

    @Override
    public Long count() {
        return reservationrepo.count();
    }

    @Override
    public void delete(Reservation entity) {
        reservationrepo.delete(entity);
    }

    @Override
    public void deleteAll() {
        reservationrepo.deleteAll();
    }

    @Override
    public Optional<Reservation> findById(int id) {
        return reservationrepo.findById(id);
    }

    @Override
    public Reservation save(Reservation entity) {
        return reservationrepo.save(entity);
    }

    @Override
    public List<Reservation> saveAll(List<Reservation> list) {
        return (List<Reservation>) reservationrepo.saveAll(list);
    }


    @Override
    public List<Reservation> findAllByStartDateGreaterThan(LocalDate endDate) {
        return reservationrepo.findAllByStartDateGreaterThan(endDate);
    }

    @Override
    public void deleteByResNr(int resnr) {
        reservationrepo.deleteByResNr(resnr);
    }
}

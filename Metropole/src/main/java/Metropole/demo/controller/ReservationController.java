package Metropole.demo.controller;

import Metropole.demo.model.Reservation;
import Metropole.demo.service.Reservationservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Transactional
@RestController
public class ReservationController {
    @Autowired
    private Reservationservice reservationservice;
   @GetMapping("/reservations")
    public List<Reservation> findAll(){
        return this.reservationservice.findAll();
    }
    @GetMapping("/reservations/search/{id}")
    public Optional<Reservation> findById(@PathVariable int id) {
       return reservationservice.findById(id);
    }
    /////////////////////////bug

@GetMapping("/reservations/present")
public List<Reservation> findAllByStartDateGreaterThan(LocalDate date){
    LocalDateTime localDateTime = LocalDateTime.now(); //getting sysdate

    LocalDate localdate = localDateTime.toLocalDate();
    return reservationservice.findAllByStartDateGreaterThan(localdate);
}
    @PostMapping("/reservations/add")
    public Reservation save(@RequestBody Reservation reservation) {
       return reservationservice.save(reservation);
    }
    @PutMapping("/reservations/edit/save")
    public Reservation edit(@RequestBody Reservation reservation) {
        return reservationservice.save(reservation);
    }
    @PutMapping("/reservations/saveall")
    public List<Reservation> saveall(@RequestBody List<Reservation> reservations) {
       return reservationservice.saveAll(reservations);
    }
    @DeleteMapping("/reservations/delete/{id}")
    public void delete(@PathVariable int id) {
        reservationservice.deleteByResNr(id);
    }
}

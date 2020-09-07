package Metropole.demo.repositories;


import Metropole.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface Clientrepo extends CrudRepository<Client, Integer>, JpaRepository<Client,Integer> {

    List<Client>findAllByEndDateGreaterThanEqualAndStartDateLessThanEqual (LocalDate endDate, LocalDate startDate);
    List<Client> findAllByRoomRoomId(int id);
    Optional<Client> findByFullName(String fullname);
}

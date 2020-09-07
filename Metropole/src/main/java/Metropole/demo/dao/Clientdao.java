package Metropole.demo.dao;



import Metropole.demo.model.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Clientdao {
    List<Client> findAll();
    Long count();
    void delete(Client entity);
    void deleteById(int id);
    Optional<Client> findById(int id);
    Client save(Client entity);
    List<Client> saveAll(List<Client> list);
    Optional<Client> findByFullName(String fullname);
    List<Client>findAllByEndDateGreaterThanEqualAndStartDateLessThanEqual(LocalDate endDate, LocalDate startDate);
    List<Client> findAllByRoomRoomId(int id);

}

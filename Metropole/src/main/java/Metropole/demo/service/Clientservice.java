package Metropole.demo.service;

import Metropole.demo.dao.Clientdao;
import Metropole.demo.model.Client;

import Metropole.demo.repositories.Clientrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Clientservice implements Clientdao {
    @Autowired
    private Clientrepo clientrepo;

    @Autowired
    private Roomservice roomservice;

    @Override
    public List<Client> findAll() {
        LocalDateTime localDateTime = LocalDateTime.now(); //getting sysdate

        LocalDate localdate = localDateTime.toLocalDate();
         clientrepo.findAll().forEach(client -> {
             if ((client.getEndDate().compareTo(localdate)) < 0){
                 client.setRoom(null);
                 
         }});
         return clientrepo.findAll();
    }

    @Override
    public Long count() {
        return clientrepo.count();
    }

    @Override
    public void delete(Client entity) {
        clientrepo.delete(entity);
    }

    @Override
    public void deleteById(int id) {
        clientrepo.deleteById(id);
    }

    @Override
    public Optional<Client> findById(int id) {
        return clientrepo.findById(id);
    }

    @Override
    public Client save(Client client) {
        if(client.getRoom()!=null)
            roomservice.save(client.getRoom()).setStatus("occupied");
        return clientrepo.save(client);
    }

    @Override
    public List<Client> saveAll(List<Client> list) {
        return (List<Client>) clientrepo.saveAll(list);
    }

    @Override
    public Optional<Client> findByFullName(String fullname) {
        return this.clientrepo.findByFullName(fullname);
    }


    @Override
    public List<Client> findAllByEndDateGreaterThanEqualAndStartDateLessThanEqual(LocalDate endDate, LocalDate startDate) {
        return clientrepo.findAllByEndDateGreaterThanEqualAndStartDateLessThanEqual(endDate,startDate);
    }

    @Override
    public List<Client> findAllByRoomRoomId(int id) {
        return clientrepo.findAllByRoomRoomId(id);
    }

}

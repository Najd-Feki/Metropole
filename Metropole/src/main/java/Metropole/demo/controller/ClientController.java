package Metropole.demo.controller;

import Metropole.demo.model.Client;
import Metropole.demo.model.Room;
import Metropole.demo.service.Clientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private Clientservice clientservice;

    @GetMapping("/clients")
    public List<Client> findAll() {
        return this.clientservice.findAll();
    }


    @GetMapping ("/clients/count")
    public Long count() {
        return clientservice.count();
    }

    @GetMapping("/clients/present")
    public List<Client> findAllByEndDateGreaterThanAndStartDateLessThan(){
        LocalDateTime localDateTime = LocalDateTime.now(); //getting sysdate

        LocalDate localdate = localDateTime.toLocalDate();
        return clientservice.findAllByEndDateGreaterThanEqualAndStartDateLessThanEqual(localdate,localdate);
    }
    @GetMapping("/clients/search/name/{fullname}")
    public Optional<Client> findbyname(@PathVariable String fullname) {
        return this.clientservice.findByFullName(fullname);
    }
    @DeleteMapping("/clients/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") int id ) {
        if (!clientservice.findById(id).isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        clientservice.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/clients/saveall")
    public List<Client> saveAll(@RequestBody List<Client> clientlist) {
        return clientservice.saveAll(clientlist);
    }

    @PutMapping("/clients/edit/save")
    public Client save(@RequestBody Client client) {
        return clientservice.save(client);
    }
    @GetMapping("/clients/room/{roomid}")
    List<Client> findAllByRoomRoomId(@PathVariable int roomid){
        return clientservice.findAllByRoomRoomId(roomid);
    }
    @GetMapping("/clients/search/{id}")
    public Optional<Client> getClient(@PathVariable int id) {return clientservice.findById(id);}
    @PostMapping("/clients/add")
    public Client addCLient(@RequestBody Client client) {return clientservice.save(client);}
}

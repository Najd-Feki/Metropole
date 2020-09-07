package Metropole.demo.controller;

import Metropole.demo.model.Room;

import Metropole.demo.service.Roomservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    @Autowired
    private Roomservice roomservice;
    @GetMapping("/rooms")
    public List<Room> findAll() {
        return this.roomservice.findAll();
    }
    @GetMapping("/rooms/status/{status}")
    public List<Room> findAllByStatus(@PathVariable("status") String status) {
        return roomservice.findAllByStatus(status);
    }
    @GetMapping("/rooms/roomtype/{roomtype}")
    public List<Room> findAllByRoomType(@PathVariable("roomtype") String roomtype) {
        return roomservice.findAllByStatus(roomtype);
    }
    @GetMapping("/rooms/{status}/{roomtype}")
    public List<Room> findAllByStatusAndRoomType(@PathVariable("status") String status,@PathVariable("roomtype") String roomtype) {
        return roomservice.findAllByStatusAndRoomType(status,roomtype);
    }
    @DeleteMapping("/rooms/delete/{id}")
    public void deleteById(@PathVariable("id") int id){
        roomservice.deleteById(id);
        System.out.println("niggacat");
    }
    @PostMapping("/rooms/saveall")
    public List<Room> saveAll(@RequestBody List<Room> rooms) {
        return roomservice.saveAll(rooms);
    }
    @PutMapping("/rooms/edit/save")
    public Room edit(@RequestBody Room room){
        return roomservice.save(room);
    }
    @PutMapping("/rooms/emptying/{id}")
    public void emptyingRoom(@PathVariable int id){
        roomservice.emptyingRoom(id);
    }
    @GetMapping("/rooms/search/{id}")
    public Optional<Room> findById(@PathVariable int id) {
        return roomservice.findById(id);
    }
    @PostMapping("/rooms/add")
    public Room save(@RequestBody Room room) {return roomservice.save(room);}
}

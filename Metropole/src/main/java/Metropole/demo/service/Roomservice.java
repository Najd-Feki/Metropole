package Metropole.demo.service;

import Metropole.demo.dao.Roomdao;

import Metropole.demo.model.Client;
import Metropole.demo.model.Room;

import Metropole.demo.repositories.Roomrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Roomservice implements Roomdao {

    @Autowired
    private Roomrepo roomrepo;

    @Autowired
    Clientservice clientservice;

    @Override
    public List<Room> findAll() {
        return (List<Room>) roomrepo.findAll();
    }

    @Override
    public Long count() {
        return roomrepo.count();
    }

    @Override
    public void delete(Room entity) {
        roomrepo.delete(entity);
    }

    @Override
    public void deleteById(int id) {
        roomrepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roomrepo.deleteAll();
    }

    @Override
    public Optional<Room> findById(int id) {
        return roomrepo.findById(id);
    }

    @Override
    public Room save(Room entity) {
        return roomrepo.save(entity);
    }

    @Override
    public List<Room> saveAll(List<Room> list) {
        return (List<Room>) roomrepo.saveAll(list);
    }

    @Override
    public List<Room> findAllByStatus(String status) {
        return roomrepo.findAllByStatus(status);
    }

    @Override
    public List<Room> findAllByRoomType(String roomType) {
        return roomrepo.findAllByRoomType(roomType);
    }

    @Override
    public List<Room> findAllByStatusAndRoomType(String status, String roomType) {
        return roomrepo.findAllByStatusAndRoomType(status, roomType);
    }

    @Override
    public void emptyingRoom(int id) {
        clientservice.findAllByRoomRoomId(id).forEach(client -> {
            client.setRoom(null);
        clientservice.save(client);});
        /*
        roomrepo.findById(id).stream().forEach(room -> {
            System.out.println(room.getRoomId());
            room.getClients().forEach(client -> {
                System.out.println(client.getFullName());
                client.setRoom(null);
                clientservice.save(client);
            });
        });*/
    }
}

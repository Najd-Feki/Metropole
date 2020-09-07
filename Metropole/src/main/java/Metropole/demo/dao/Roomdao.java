package Metropole.demo.dao;

import Metropole.demo.model.Room;

import java.util.List;
import java.util.Optional;

public interface Roomdao {
    List <Room> findAll();
    Long count();
    void delete(Room entity);
    void deleteById(int id);
    void deleteAll();
    Optional<Room> findById(int id);
    Room save(Room entity);
    List<Room> saveAll(List<Room> list);
    List<Room> findAllByStatus(String status);
    List<Room> findAllByRoomType(String roomType);
    List<Room> findAllByStatusAndRoomType(String status, String roomType);
    void emptyingRoom(int id);
}

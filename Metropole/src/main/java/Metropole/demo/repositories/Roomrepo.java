package Metropole.demo.repositories;

import Metropole.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Roomrepo extends CrudRepository<Room, Integer>, JpaRepository<Room,Integer> {
    List<Room> findAllByStatus(String status);
    List<Room> findAllByRoomType(String roomType);
    List<Room> findAllByStatusAndRoomType(String status, String roomType);
}

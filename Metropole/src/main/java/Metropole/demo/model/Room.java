package Metropole.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "roomid", nullable = false)
    private int roomId;
    @Column(name = "roomtype", length = 45, nullable = false)
    private String roomType;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "rate",nullable = false)
    private int rate;
    @Column(name = "resnr",nullable = true)
    private Integer resnr;

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Integer getResnr() {
        return resnr;
    }

    public void setResnr(Integer resnr) {
        this.resnr = resnr;
    }

    public Room(int roomId, String roomType, String status, int rate, Integer resnr) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.status = status;
        this.rate = rate;
        this.resnr = resnr;
    }
}
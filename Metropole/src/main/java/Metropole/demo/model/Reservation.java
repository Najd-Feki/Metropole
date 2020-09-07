package Metropole.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "resnr" , nullable = false)
    private int resNr;
    @OneToMany()
    @JoinColumn(name="resnr",nullable = true)
    private List<Room> room;
    @ManyToOne
    @JoinColumn(name = "employeeid", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "clientid")
    private Client client;
    @Column(name = "nombrepr", nullable = false)
    private int nombrePR;
    @Column(name ="startdate",nullable = false)
    private LocalDate startDate ;
    @Column(name ="enddate",nullable = false)
    private LocalDate endDate;
    @Column(name = "fullname")
    private String fullName;
    @Column(name="children")
    private int children;
    @Column(name="breakfast")
    private boolean breakfast;
    @Column(name = "comment")
    private String comment;
    @Column(name = "confirmed")
    private boolean confirmed;
    @Column(name = "createdat",insertable = false)
    private Date createdAt;
    @Column(name = "type")
    private String type;

    public Reservation() {
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getResNr() {
        return resNr;
    }

    public void setResNr(int resNr) {
        this.resNr = resNr;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNombrePR() {
        return nombrePR;
    }

    public void setNombrePR(int nombrePR) {
        this.nombrePR = nombrePR;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Reservation(int resNr, List<Room> room, Employee employee, Client client, int nombrePR, LocalDate startDate, LocalDate endDate, String fullName, int children, boolean breakfast, String comment, boolean confirmed, Date createdAt, String type) {
        this.resNr = resNr;
        this.room = room;
        this.employee = employee;
        this.client = client;
        this.nombrePR = nombrePR;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullName = fullName;
        this.children = children;
        this.breakfast = breakfast;
        this.comment = comment;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
        this.type = type;
    }
}

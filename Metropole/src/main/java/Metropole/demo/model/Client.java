package Metropole.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid", length = 45, nullable = false)
    private int clientId;


    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Column(name = "phone", nullable = true)
    private Integer Phone;
    @Column(name = "nationality", nullable = false)
    private String Nationality;
    @ManyToOne
    @JoinColumn(name = "roomid", nullable = true)
    private Room room;
    @Column(name = "cinpass", length = 45, nullable = false)
    private String cinPass;
    @Column(name = "dob", nullable = false)
    private LocalDate dob;
    @Column(name = "dateissue", nullable = false)
    private LocalDate dateIssue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billid", nullable = true)
    private Bill bills;
    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;
    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;


    public Client() {
    }

    public Client(int clientId, String fullName, Integer phone, String nationality, Room room, String cinPass, LocalDate dob, LocalDate dateIssue, Bill bills, LocalDate startDate, LocalDate endDate) {
        this.clientId = clientId;
        this.fullName = fullName;
        Phone = phone;
        Nationality = nationality;
        this.room = room;
        this.cinPass = cinPass;
        this.dob = dob;
        this.dateIssue = dateIssue;
        this.bills = bills;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPhone() {
        return Phone;
    }

    public void setPhone(Integer phone) {
        Phone = phone;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getCinPass() {
        return cinPass;
    }

    public void setCinPass(String cinPass) {
        this.cinPass = cinPass;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(LocalDate dateIssue) {
        this.dateIssue = dateIssue;
    }

    public Bill getBills() {
        return bills;
    }

    public void setBills(Bill bills) {
        this.bills = bills;
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


}


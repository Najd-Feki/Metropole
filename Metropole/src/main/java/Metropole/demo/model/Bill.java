package Metropole.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "billid",nullable = false)
    private int billId;

    @Column(name = "roomcharge",nullable = false)
    private int roomCharge;

    @Column(name = "misccharge",nullable = true)
    private int miscCharge;

    @Column(name="paid",nullable = true)
    private int paid;

    @Column(name = "resttopay",nullable = true)
    private int restTopay;

    @ManyToOne
    @JoinColumn(name = "employeeid",nullable = false)
    private Employee employee;

    @Column(name = "startdate",nullable = false)
    private LocalDate startDate;

    @Column(name = "enddate",nullable = false)
    private LocalDate endDate;


    public Bill() {
    }

    public Bill(int billId, int roomCharge, int miscCharge, int paid, int restTopay, Employee employee, LocalDate startDate, LocalDate endDate) {
        this.billId = billId;
        this.roomCharge = roomCharge;
        this.miscCharge = miscCharge;
        this.paid = paid;
        this.restTopay = restTopay;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(int roomCharge) {
        this.roomCharge = roomCharge;
    }

    public int getMiscCharge() {
        return miscCharge;
    }

    public void setMiscCharge(int miscCharge) {
        this.miscCharge = miscCharge;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getRestTopay() {
        return restTopay;
    }

    public void setRestTopay(int restTopay) {
        this.restTopay = restTopay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

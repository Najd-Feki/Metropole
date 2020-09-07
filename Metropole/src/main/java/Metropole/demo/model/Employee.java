package Metropole.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid", nullable = false)
    private int employeeId;
    @Column(name = "fullname", length = 45, nullable = false)
    private String fullName;
    @Column(name = "username", length = 45, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "phone", nullable = false)
    private int phone;
    @Column(name = "cin", nullable = false)
    private int cin;
    @Column(name = "dob", nullable = false)
    private String dob;
    @ManyToMany(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name="userrole",
            joinColumns={@JoinColumn(name="employeeid", referencedColumnName="employeeid")},
            inverseJoinColumns={@JoinColumn(name="roleid", referencedColumnName="id")})
    private List<Role> roles;

    public Employee() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Employee(int employeeId, String fullName, String username, String password, int phone, int cin, String dob, List<Role> roles) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.cin = cin;
        this.dob = dob;
        this.roles = roles;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

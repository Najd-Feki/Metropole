package Metropole.demo.repositories;

import Metropole.demo.model.Bill;
import Metropole.demo.model.Client;
import Metropole.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Employeerepo extends CrudRepository<Employee, Integer> , JpaRepository<Employee,Integer> {
Employee findByUsername(String username);
}

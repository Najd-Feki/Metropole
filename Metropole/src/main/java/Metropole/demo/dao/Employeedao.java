package Metropole.demo.dao;

import Metropole.demo.model.Employee;


import java.util.List;
import java.util.Optional;

public interface Employeedao {
    List<Employee> findAll();
    Long count();
    void delete(Employee entity);
    void deleteAll();
    Optional<Employee> findById(int id);
    Employee save(Employee entity);
    List<Employee> saveAll(List<Employee> list);
    void deleteById(int id);
    Employee findByUsername(String username);
}

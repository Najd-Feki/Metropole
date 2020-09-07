package Metropole.demo.dao;



import Metropole.demo.model.Bill;

import java.util.List;
import java.util.Optional;

public interface Billdao {
    List<Bill> findAll();
    Long count();
    void delete(Bill entity);
    void deleteAll();
    Optional<Bill> findById(int id);
    Bill save(Bill entity);
    List<Bill> saveAll(List<Bill> list);
    Optional<Bill> findByClient_FullName(String Full_name);
}

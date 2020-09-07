package Metropole.demo.repositories;

import Metropole.demo.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Billrepo extends JpaRepository<Bill,Integer>{
}

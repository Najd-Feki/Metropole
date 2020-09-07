package Metropole.demo.service;

import Metropole.demo.dao.Billdao;
import Metropole.demo.model.Bill;
import Metropole.demo.repositories.Billrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class Billservice implements Billdao {

    @Autowired
    private Billrepo billrepo;

    @Override
    public List<Bill> findAll() {
        return (List<Bill>) billrepo.findAll();
    }

    @Override
    public Long count() {
        return billrepo.count();
    }

    @Override
    public void delete(Bill entity) {
        billrepo.delete(entity);
    }

    @Override
    public void deleteAll() {
        billrepo.deleteAll();
    }

    @Override
    public Optional<Bill> findById(int id) {
        return billrepo.findById(id);
    }

    @Override

    public Bill save(@NotNull Bill entity) {
        return billrepo.save(entity);
    }

    @Override
    public List<Bill> saveAll(List<Bill> list) {
        return (List<Bill>) billrepo.saveAll(list);
    }

    @Override
    public Optional<Bill> findByClient_FullName(String Full_name) {
        return null;
    }


}

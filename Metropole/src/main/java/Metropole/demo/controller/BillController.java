package Metropole.demo.controller;

import Metropole.demo.model.Bill;
import Metropole.demo.service.Billservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private Billservice billservice;

    @GetMapping("/bills")
    public List<Bill> findAll() {
        return billservice.findAll();
    }

    @GetMapping("/bills/{id}")
        public Optional<Bill> findById(@PathVariable("id") String id){
        int billId = Integer.parseInt(id);
        return billservice.findById(billId);
    }
    @GetMapping("/bills/{name}")
    public Optional<Bill> findByClient_FullName(@PathVariable("name") String name){
        return billservice.findByClient_FullName(name);
    }

   /* @PostMapping("/create_bill")
    public Bill save(@RequestBody){
        return billservice.save()
    }


    */

}

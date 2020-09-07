package Metropole.demo.service;

import Metropole.demo.dao.Employeedao;

import Metropole.demo.model.Employee;
import Metropole.demo.repositories.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class Employeeservice implements Employeedao, UserDetailsService {
    @Autowired
    private Employeerepo employeerepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeerepo.findAll();
    }

    @Override
    public Long count() {
        return employeerepo.count();
    }

    @Override
    public void delete(Employee entity) {
        employeerepo.delete(entity);
    }

    @Override
    public void deleteAll() {
        employeerepo.deleteAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeerepo.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
       employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
        return employeerepo.save(employee);
    }

    @Override
    public List<Employee> saveAll(List<Employee> list) {
        list.forEach(employee -> employee.setPassword(bcryptEncoder.encode(employee.getPassword())));
        return (List<Employee>) employeerepo.saveAll(list);
    }

    @Override
    public void deleteById(int id) {
        employeerepo.deleteById(id);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeerepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeerepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(Employee user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}

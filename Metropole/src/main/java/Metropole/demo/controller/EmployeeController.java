package Metropole.demo.controller;

import Metropole.demo.model.Employee;
import Metropole.demo.security.JwtTokenUtil;
import Metropole.demo.service.Employeeservice;
import Metropole.demo.user.JwtRequest;
import Metropole.demo.user.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private Employeeservice employeeservice;

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeservice.findAll();
    }
    @GetMapping("/employees/search/name/{name}")
    public Employee findByUserName(@PathVariable String name){return this.employeeservice.findByUsername(name);}

    @GetMapping("/employees/search/{id}")
    public Optional<Employee> findById(@PathVariable("id") int id) {
        return employeeservice.findById(id);
    }
    @PostMapping("/employees/add")
    public Employee save(@RequestBody Employee employee) {
        return employeeservice.save(employee);
    }

    @PostMapping("/employees/saveall")
    public List<Employee> saveAll(@RequestBody List<Employee> employees) {
        return employeeservice.saveAll(employees);
    }

    @PutMapping("/employees/edit/save")
    public Employee edit(@RequestBody Employee employee) {
        return employeeservice.save(employee);}

    @DeleteMapping("/employees/delete/{id}")
    public void delete(@PathVariable int id){
         employeeservice.deleteById(id);
    }



    ///
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private Employeeservice userDetailsService;
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody Employee employee) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(employee));
    }
//////////////////
@GetMapping("/username")
public String currentUserNameSimple(HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    return principal.getName();
    }
    @GetMapping("/user")
    public Employee currentUserSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return findByUserName(principal.getName());
    }
}


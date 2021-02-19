package org.iainuk.tracker.controller;

import org.iainuk.tracker.Customer;
import org.iainuk.tracker.dao.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/customers")
    public List<Customer> showCustomerList() {

        List<Customer> customers = customerRepo.findAllByOrderByFirstName();

        return customers;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Optional<Customer> optCust = customerRepo.findById(id);
        if (optCust.isPresent()) {
            return new ResponseEntity<>(optCust.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@Valid @RequestBody Customer customer) {

        customer.setId(0);

        customerRepo.save(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer putCustomer(@RequestBody Customer customer) {

        customerRepo.save(customer);

        return customer;
    }

    @PatchMapping("/customers")
    public Customer patchCustomer(@RequestParam("id") int id, @RequestBody Customer patch) {

        Customer customer = customerRepo.findById(id).get();
        if (patch.getFirstName() != null) {
            customer.setFirstName(patch.getFirstName());
        }
        if (patch.getLastName() != null) {
            customer.setLastName(patch.getLastName());
        }
        if (patch.getEmail() != null) {
            customer.setEmail(patch.getEmail());
        }

        customerRepo.save(customer);

        return customer;
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {

        try {
            customerRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}

    }
}

package com.targetindia.controllers;

import com.targetindia.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

// @RestController
// @RequestMapping
// @GetMapping
// @PostMapping
// @PathVariable
// @RequestBody
// ResponseEntity

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    private List<Customer> customers;

    public CustomersController() {
        customers = new ArrayList<>();
        customers.add(new Customer("8bec3fb4-ae9e-423e-95c1-aef2d6ddd093", "Vinod Kumar", "vinod@vinod.co", "9731424784", "Bangalore"));
        customers.add(new Customer("0eb31b3d-dde3-4138-9c6e-124fe7264278", "Shyam Sundar", "shyam@xmpl.com", "9730004784", "Shivamogga"));
        customers.add(new Customer("d85be6a9-1715-4106-a9f9-d4e3118819b4", "John Doe", "johndoe@xmpl.com", "9731421234", "Dallas"));
    }

    @GetMapping(produces = "application/json")
    public List<Customer> handleGetAll() {
        return customers;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity handleGetOne(@PathVariable String id) {
        Customer customer = customers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            Map<String, Object> err = new LinkedHashMap<>();
            err.put("message", "no customer data found for id " + id);
            err.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity handleDeleteOne(@PathVariable String id) {
        Customer customer = customers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            Map<String, Object> err = new LinkedHashMap<>();
            err.put("message", "no customer data found for id " + id);
            err.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }
        customers.remove(customer);
        return ResponseEntity.ok(customers);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity handlePutOne(@PathVariable String id,@RequestBody Customer customer) {

        // do some validation
        Map<String, Object> err = new LinkedHashMap<>();

        Customer cust = customers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (cust == null) {
            err.put("message", "no customer data found for id " + id);
            err.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }

        // name/email are mandatory
        if (customer.getName() == null || customer.getEmail() == null) {
            err.put("message", "name/email are mandatory fields");
            err.put("timestamp", new Date());
            return ResponseEntity.badRequest().body(err);
        }

        // email should be unique
        var oldEmail = cust.getEmail();
        if(customer.getEmail().equals(oldEmail) == false)
        {
            cust.setEmail("");
            var customerCount = customers.stream()
                    .filter(c -> c.getEmail().equals(customer.getEmail()))
                    .count();
            cust.setEmail(oldEmail);
            if (customerCount > 0) {
                err.put("message", "a customer with this email already present in our database");
                err.put("timestamp", new Date());
                return ResponseEntity.badRequest().body(err);
            }
            cust.setEmail(customer.getEmail());
        }

        // phone if not null, should be unique
        if(customer.getPhone()!=null){

            var oldPhone = cust.getPhone();
            if(customer.getPhone().equals(oldPhone) == false) {
                cust.setPhone("");
                var customerCount = customers.stream()
                        .filter(c -> c.getPhone()!=null && c.getPhone().equals(customer.getPhone()))
                        .count();
                cust.setPhone(oldPhone);
                if (customerCount > 0) {
                    err.put("message", "a customer with this phone already present in our database");
                    err.put("timestamp", new Date());
                    return ResponseEntity.badRequest().body(err);
                }
                cust.setPhone(customer.getPhone());
            }

        }
        //name ,email are mandatory
        if (cust.getName().equals(customer.getName()) == false )
            cust.setName(customer.getName());

        //city
        //update if  field has changed
        if( customer.getCity() != null) {
            if (customer.getCity().equals(cust.getCity()) == false)
                cust.setCity(customer.getCity());
        }

        return ResponseEntity.ok(cust);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity handlePatchOne(@PathVariable String id,@RequestBody Customer customer) {

        // do some validation
        Map<String, Object> err = new LinkedHashMap<>();

        Customer cust = customers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (cust == null) {
            err.put("message", "no customer data found for id " + id);
            err.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }


        // email should be unique
        if( customer.getEmail() != null) {
            var oldEmail = cust.getEmail();
            if (customer.getEmail().equals(oldEmail) == false) {
                cust.setEmail("");
                var customerCount = customers.stream()
                        .filter(c -> c.getEmail().equals(customer.getEmail()))
                        .count();
                cust.setEmail(oldEmail);
                if (customerCount > 0) {
                    err.put("message", "a customer with this email already present in our database");
                    err.put("timestamp", new Date());
                    return ResponseEntity.badRequest().body(err);
                }
                cust.setEmail(customer.getEmail());
            }
        }

        // phone if not null, should be unique
        if(customer.getPhone()!=null){
            var oldPhone = cust.getPhone();
            if(customer.getPhone().equals(oldPhone) == false) {
                cust.setPhone("");
                var customerCount = customers.stream()
                        .filter(c -> c.getPhone()!=null && c.getPhone().equals(customer.getPhone()))
                        .count();
                cust.setPhone(oldPhone);
                if (customerCount > 0) {
                    err.put("message", "a customer with this phone already present in our database");
                    err.put("timestamp", new Date());
                    return ResponseEntity.badRequest().body(err);
                }
                cust.setPhone(customer.getPhone());
            }

        }


        //name ,email are mandatory
        if( customer.getName() != null) {
            if (cust.getName().equals(customer.getName()) == false )
                cust.setName(customer.getName());
        }

        //city
        //update if  field has changed
        if( customer.getCity() != null) {
            if (customer.getCity().equals(cust.getCity()) == false)
                cust.setCity(customer.getCity());
        }

        return ResponseEntity.ok(cust);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity handlePost(@RequestBody Customer customer) throws URISyntaxException {
        // do some validation
        Map<String, Object> err = new LinkedHashMap<>();
        // name/email are mandatory
        if (customer.getName() == null || customer.getEmail() == null) {
            err.put("message", "name/email are mandatory fields");
            err.put("timestamp", new Date());
            return ResponseEntity.badRequest().body(err);
        }
        // email should be unique
        var customerCount = customers.stream()
                .filter(c -> c.getEmail().equals(customer.getEmail()))
                .count();
        if (customerCount > 0) {
            err.put("message", "a customer with this email already present in our database");
            err.put("timestamp", new Date());
            return ResponseEntity.badRequest().body(err);
        }
        // phone if not null, should be unique
        if(customer.getPhone()!=null){
            customerCount = customers.stream()
                    .filter(c -> c.getPhone()!=null && c.getPhone().equals(customer.getPhone()))
                    .count();
            if (customerCount > 0) {
                err.put("message", "a customer with this phone already present in our database");
                err.put("timestamp", new Date());
                return ResponseEntity.badRequest().body(err);
            }
        }

        customer.setId(UUID.randomUUID().toString());
        customers.add(customer);
        return ResponseEntity.created(new URI("/api/customers/" + customer.getId())).body(customer);
    }


}

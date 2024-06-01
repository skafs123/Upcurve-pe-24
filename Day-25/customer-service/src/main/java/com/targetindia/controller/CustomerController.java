package com.targetindia.controller;

import com.targetindia.entity.Customer;
import com.targetindia.exception.ErrorResponse;
import com.targetindia.exception.ServiceException;
import com.targetindia.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<Customer> handlePostOne(@Valid @RequestBody Customer customer) {
        Customer addedCustomer = service.addNewCustomer(customer);
        return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> handlePut(@PathVariable UUID id,@Valid @RequestBody Customer customer) {
        Customer updatedCustomer = service.updateWholeCustomer(id,customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> handlePatch(@PathVariable UUID id,  @RequestBody Customer customer) {
            Customer updatedCustomer = service.updateCustomer(id,customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity handleDeleteOne(@PathVariable UUID id) {
        service.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted  customer  with id :" + id);
        //return ResponseEntity.ok(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity handleGetById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(service.getCustomerById(id));
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity handleGetAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name="sort_by", defaultValue = "name") String sortBy){
        return ResponseEntity.ok(service.getAllCustomers(page, size, sortBy));
    }

    /*@GetMapping("/search")
    public List<Customer> handleGetByCity(@RequestParam String city){
        return service.getCustomersByCity(city);
    }
*/
    @GetMapping("/search")
    public ResponseEntity handleGetBySearchType(@RequestParam(name="search_type" ) String searchType,@RequestParam(name="key" ) String searchKey){
        if(searchType.equals("city"))
            return ResponseEntity.ok(service.getCustomersByCity(searchKey));
        if(searchType.equals("name"))
            return ResponseEntity.ok( service.getCustomersByName(searchKey));
        if(searchType.equals("email"))
            return ResponseEntity.ok(service.getCustomerByEmail(searchKey));
        if(searchType.equals("phone"))
            return ResponseEntity.ok(service.getCustomerByPhone(searchKey));
            //return Collections.singletonList(service.getCustomerByPhone(searchKey));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("customer not found"));
    }

    // TODO: complete this application by handling PUT, PATCH, and DELETE requests
    // TODO: see if you can handle /search in a generic way (for example, search by email or phone or city)
}

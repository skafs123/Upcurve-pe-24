package com.targetindia.repository;

import com.targetindia.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // create method signatures in findByFieldName (or findAllByFieldName) pattern
    // choose the return type as either Customer or List<Customer>
    // this method takes exactly one argument that is compared with the fieldName
    public List<Customer> findAllByCity(String city);
    public  List<Customer>  findAllByName(String name);
    public Customer findByPhone(String phone);
    public Customer findByEmail(String email);
    
}

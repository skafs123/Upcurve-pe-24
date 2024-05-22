package com.targetindia.service;

import com.targetindia.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/*

A component is a class annotated with one of the following:

@Component                  --> generic; can be applied for any class that needs spring's attention
    @Repository             --> meant for DAO classes
    @Service                --> meant for business or application logic classes
    @Controller             --> meant for mvc controllers
        @RestController     --> meant for rest controller
    @Configuration          --> meant to contain certain configuration (such as security)

Spring boot will automatically create and load the objects of these classes, during the bootstrap.
This component scan happens only inside the package "com.targetindia". Any components that are
outside of this package, need to be scanned explicitly using @ComponentScan
*/

@Slf4j
@Service
public class CustomerService {
    // this class represents business or application logic
    // this is independent of any web or spring api

    private List<Customer> customers;

    public CustomerService() {
        log.debug("CustomerService instantiated");
        customers = new ArrayList<>();
        customers.add(new Customer("8bec3fb4-ae9e-423e-95c1-aef2d6ddd093", "Vinod Kumar", "vinod@vinod.co", "9731424784", "Bangalore"));
        customers.add(new Customer("0eb31b3d-dde3-4138-9c6e-124fe7264278", "Shyam Sundar", "shyam@xmpl.com", "9730004784", "Shivamogga"));
        customers.add(new Customer("d85be6a9-1715-4106-a9f9-d4e3118819b4", "John Doe", "johndoe@xmpl.com", "9731421234", "Dallas"));
    }

    public List<Customer> getAllCustomers() throws ServiceException {
        return customers;
    }


    public Customer getCustomerById(String id) {
        return customers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Customer addNewCustomer(Customer customer) throws ServiceException{
        // do some validation
        // name/email are mandatory
        if (customer.getName() == null || customer.getEmail() == null) {
            throw new ServiceException("name/email are mandatory fields");
        }
        // email should be unique
        var customerCount = customers.stream()
                .filter(c -> c.getEmail().equals(customer.getEmail()))
                .count();
        if (customerCount > 0) {
            throw new ServiceException("a customer with this email already present in our database");
        }
        // phone if not null, should be unique
        if (customer.getPhone() != null) {
            customerCount = customers.stream()
                    .filter(c -> c.getPhone() != null && c.getPhone().equals(customer.getPhone()))
                    .count();
            if (customerCount > 0) {
                throw new ServiceException("a customer with this phone already present in our database");
            }
        }

        customer.setId(UUID.randomUUID().toString());
        customers.add(customer);
        return customer;
    }


    public Customer updateWholeCustomer( String id,Customer customer)throws ServiceException {

        // do some validation
        Map<String, Object> err = new LinkedHashMap<>();

        Customer cust = getCustomerById(id);

        if (cust == null) {
            throw new ServiceException("no customer data found for id " + id);
        }

        // name/email are mandatory
        if (customer.getName() == null || customer.getEmail() == null) {
            throw new ServiceException("name/email are mandatory fields");
        }

        // email should be unique
        if(customer.getEmail().equals(cust.getEmail()) == false)
        {
            // check if the email given is not matching with any other emails
            var customerCount = customers.stream()
                    .filter(c -> !c.getId().equals(id) && c.getEmail().equals(customer.getEmail()))
                    .count();
            if (customerCount > 0) {
                throw new ServiceException("a customer with this email already present in our database");
            }
        }

        // phone if not null, should be unique
        if(customer.getPhone()!=null){
            if(customer.getPhone().equals(cust.getPhone()) == false) {
                var customerCount = customers.stream()
                        .filter(c -> !c.getId().equals(id) &&  c.getPhone()!=null && c.getPhone().equals(customer.getPhone()))
                        .count();
                if (customerCount > 0) {
                    throw new ServiceException( "a customer with this phone already present in our database");
                }
            }

        }
        var index = customers.indexOf(cust);
        customer.setId(id);
        customers.set(index, customer);
        return customer;
    }

        public Customer updateCustomer( String id,Customer customer)throws ServiceException {

        // do some validation
        Map<String, Object> err = new LinkedHashMap<>();

        Customer cust = getCustomerById(id);

        if (cust == null) {
            throw new ServiceException("no customer data found for id " + id);
        }


        // email should be unique
        if( customer.getEmail() != null) {
            if (customer.getEmail().equals(cust.getEmail()) == false) {

                var customerCount = customers.stream()
                        .filter(c -> !c.getId().equals(id) &&  c.getEmail().equals(customer.getEmail()))
                        .count();

                if (customerCount > 0) {
                    throw new ServiceException("a customer with this email already present in our database");
                }
                cust.setEmail(customer.getEmail());
            }
        }

        // phone if not null, should be unique
        if(customer.getPhone()!=null){

            if(customer.getPhone().equals(cust.getPhone()) == false) {

                var customerCount = customers.stream()
                        .filter(c -> !c.getId().equals(id) &&  c.getPhone()!=null && c.getPhone().equals(customer.getPhone()))
                        .count();

                if (customerCount > 0) {
                    throw new ServiceException( "a customer with this phone already present in our database");
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

        return cust;
    }


    public  List<Customer> deleteCustomer( String id) throws ServiceException {
        Customer customer = customers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            throw new ServiceException( "no customer data found for id " + id);
        }
        customers.remove(customer);
        return customers;
    }

}

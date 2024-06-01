package com.targetindia.service;

import com.targetindia.entity.Customer;
import com.targetindia.exception.ServiceException;
import com.targetindia.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Customer addNewCustomer(@Valid Customer customer) {
        try {
            return repo.save(customer);
        } catch (DataIntegrityViolationException ex) {
            throw new ServiceException("Email or phone number already exists");
        }
    }

    public Customer getCustomerById(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ServiceException("No customer found for id: " + id.toString()));
    }

    public Page<Customer> getAllCustomers(int page, int size, String sortBy){
        Pageable p = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, sortBy));
        return repo.findAll(p);
    }

    public List<Customer> getCustomersByCity(String city){
        return repo.findAllByCity(city);
    }
    public List<Customer> getCustomersByName(String name){
        return repo.findAllByName(name);
    }
    public Customer getCustomerByEmail(String email){
        return repo.findByEmail(email);
    }
    public Customer getCustomerByPhone(String phone){
        return repo.findByPhone(phone);
    }

    public Customer updateWholeCustomer(UUID id, Customer customer) throws ServiceException
    {
        try{
            customer.setId(id);
            return repo.save(customer);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }
    public Customer updateCustomer(UUID id, Customer customer) throws ServiceException
    {
        try{
            Customer updateCustomer = repo.findById(id).orElseThrow(() -> new ServiceException("No customer found for id: " + id.toString()));

            String temp=null;
            temp = customer.getName();
            if(temp != null)
                updateCustomer.setName(temp);
            temp = customer.getEmail();
            if(temp != null)
                updateCustomer.setEmail(temp);
            temp = customer.getPhone();
            if(temp != null)
                updateCustomer.setPhone(temp);
            temp = customer.getCity();
            if(temp != null)
                updateCustomer.setCity(temp);
            return repo.save(updateCustomer);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }

    public void deleteCustomer(UUID id) throws ServiceException
    {
       Customer customer = repo.findById(id).orElseThrow(() -> new ServiceException("No customer found for id: " + id.toString()));
       repo.delete(customer);

    }
}

package com.targetindia.dao;

import com.targetindia.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SerializationCustomerDao implements CustomerDao {

    private final Map<String, Customer> customers;
    private static final String FILENAME = "customers.bin";

    public SerializationCustomerDao() {
        this.customers = new HashMap<>();
        var customers = loadFromFile();
        if (customers == null) {
            return;
        }
        this.customers.putAll((Map<? extends String, ? extends Customer>) customers);
    }

    void saveToFile() {
        try (FileOutputStream file = new FileOutputStream(FILENAME);
             ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(customers);
        } catch (IOException e) {
            log.warn("error while saving customers HashMap to the file ", e);
        }
    }

    Object loadFromFile() {
        try (FileInputStream file = new FileInputStream(FILENAME);
             ObjectInputStream in = new ObjectInputStream(file)) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.warn("error while reading customers from file to the HashMap", e);
        }
        return null;
    }

    @Override
    public String save(Customer customer) throws DaoException {
        // auto generate the id
        var id = UUID.randomUUID().toString();
        customer.setId(id);
        customers.put(id, customer);
        saveToFile();
        return id;
    }

    @Override
    public Customer findById(String id) throws DaoException {
        return customers.get(id);
    }

    @Override
    public void update(Customer customer) throws DaoException {
        // serialize();
        throw new DaoException("method not implemented yet");

    }

    @Override
    public void delete(String id) throws DaoException {
        // serialize();
        Customer res = customers.remove(id);
        if (res != null)
            saveToFile();
        else
            throw new DaoException("Not found customer with id");
    }

    @Override
    public List<Customer> findAll() throws DaoException {
        return customers
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllByCity(String city) throws DaoException {
        return customers
                .values()
                .stream()
                .filter(customer -> customer.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public Customer findByEmail(String email) throws DaoException {
        Customer customer = null;
        try {
             customer = customers
                    .values()
                    .stream()
                    .filter(cust -> cust.getEmail().equals(email))
                    .findFirst().get();
        }catch (Exception e)
        {
            throw new DaoException("No customer with the email id is found");
        }
        return customer;


    }

    @Override
    public Customer findByPhone(String phone) throws DaoException {

        Customer customer = null;
        try {
            customer = customers
                    .values()
                    .stream()
                    .filter(cust -> cust.getPhone().equals(phone))
                    .findFirst().get();
        }catch (Exception e)
        {
            throw new DaoException("No customer with the phone no is found");
        }
        return customer;

    }

    @Override
    public List<Customer> findByAgeGroup(Integer fromAge, Integer toAge) throws DaoException {
        throw new DaoException("method not implemented yet");
    }
}

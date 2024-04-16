package com.targetindia.ui;

import com.targetindia.model.Customer;
import com.targetindia.service.CustomerService;
import com.targetindia.service.ServiceException;
import com.targetindia.utils.KeyboardUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private final CustomerService service;

    public Main() {
        service = new CustomerService();
    }

    public void start() {
        int choice;
        while ((choice = menu()) != 0) {
            switch (choice) {
                case 1:
                    addNewCustomer();
                    break;
                case 2:
                    listAll();
                    break;
                case 3://search by ID
                    findCustById();
                    break;
                case 4://Search by email
                    findCustByEmail();
                    break;
                case 5://Search by phone
                    findCustByPhone();
                    break;
                case 6://Search by city
                    findCustByCity();
                    break;
                case 7://Update customer data
                case 8://Delete customer data
                    deleteCustById();
                    break;
                default:
                    System.out.println("Invalid choice. Please retry.");
            }
        }
    }

    private void deleteCustById() {
        //get the id from input
        String id = KeyboardUtil.getString("Please input the customer id: ");
        if(id != null) {
            try{
                service.deleteCustomerById(id);
            }catch (ServiceException e) {
                log.trace("Could not find customer with id no {}", id);
                System.out.println("Could not find customer with id no: " + id);
                return;
            }
            System.out.printf("Customer with id no %s deleted" ,id);
            System.out.println();
        }
    }

    private void findCustByCity() {
        // TODO: get all customers for a city from service and print in a tabular format

        String city = KeyboardUtil.getString("Please input the customer city : ");
        if(city != null) {
            var list = service.getAllCustomersByCity(city);
            printCustomerListAsTable(list);
        }

    }

    private void findCustByPhone() {
        //get the id from input
        String phone = KeyboardUtil.getString("Please input the customer email : ");
        if(phone != null) {
            Customer customer = null;
            try{
                customer = service.findCustomerByPhone(phone);
            }catch (ServiceException e) {
                log.warn("user supplied invalid phone no.", e);
                System.out.println("Invalid phone no.");
            }
            if (customer != null) {
                List<Customer> customers = new ArrayList<>();
                customers.add(customer);
                printCustomerListAsTable(customers);
            }

        }
    }
    private void findCustByEmail() {
        //get the id from input
        String email = KeyboardUtil.getString("Please input the customer email : ");
        if(email != null) {
            Customer customer = null;
            try{
                customer = service.findCustomerByEmail(email);
            }catch (ServiceException e) {
                log.warn("user supplied invalid email id.", e);
                System.out.println("Invalid email id.");
            }
            if (customer != null) {
                List<Customer> customers = new ArrayList<>();
                customers.add(customer);
                printCustomerListAsTable(customers);
            }

        }
    }


    private void findCustById() {
        //get the id from input
        String id = KeyboardUtil.getString("Please input the customer id: ");
        if(id != null) {
            var customer = service.findCustomerById(id);
            if( customer != null) {
                List<Customer> customers = new ArrayList<>();
                customers.add(customer);
                printCustomerListAsTable(customers);
            }
            else
                System.out.println("Invalid Customer id.");

        }
    }

    private void addNewCustomer() {
        // TODO: accept customer data and call the service method to add
        System.out.println("Enter new customer details: ");
        String name = KeyboardUtil.getString("Name          : ");
        Date birthDate = null;
        try {
            birthDate = KeyboardUtil.getDate("Date of birth : ");
        } catch (Exception e) {
            log.warn("user supplied invalid birth date", e);
        }

        String email = KeyboardUtil.getString("Email         : ");
        String phone = KeyboardUtil.getString("Phone         : ");
        String city = KeyboardUtil.getString("City          : ");
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setCity(city);
        customer.setBirthDate(birthDate);

        try {
            service.addNewCustomer(customer);
            System.out.println("New customer data added successfully!");
            log.trace("New customer data added successfully with id {}", customer.getId());
        } catch (ServiceException e) {
            System.out.println("Something went wrong. Check the logs for more details.");
            log.warn("error while adding customer in the Main", e);
        }


    }

    private void listAll() {
        // TODO: get all customers from service and print in a tabular format
        var list = service.getAllCustomers();
        printCustomerListAsTable(list);
    }

    void printCustomerListAsTable(List<Customer> customers){
        // TODO: display the customer data in a tabular format as in MYSQL CLI output
        System.out.println("Customer data table");
        System.out.printf("| %-40s | %-15s | %-20s | %-10s | %-10s | %-30s |%n","ID","Name","Email","Phone","City","BirthDate");

        for(Customer customer:customers) {
            String id = customer.getId();
           String name = customer.getName();
           String email = customer.getEmail();
           String phone= customer.getPhone();
           String city = customer.getCity();
           String sBirthDate = customer.getBirthDate().toString();
           System.out.printf("| %-40s | %-15s | %-20s | %-10s | %-10s | %-30s |\n",id,name,email,phone,city,sBirthDate );

        }

    }

    int menu() {
        System.out.println("*** Main Menu ***");
        System.out.println("0. Exit");
        System.out.println("1. Add a new customer data");
        System.out.println("2. List all");
        System.out.println("3. Search by id");
        System.out.println("4. Search by email");
        System.out.println("5. Search by phone");
        System.out.println("6. Search by city");
        System.out.println("7. Update customer data");
        System.out.println("8. Delete customer data");
        try {
            return KeyboardUtil.getInt("Enter your choice: ");
        } catch (Exception e) {
            log.warn("error in choice by user", e);
            return -1;
        }
    }
}

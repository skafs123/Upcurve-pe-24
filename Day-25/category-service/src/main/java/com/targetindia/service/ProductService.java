package com.targetindia.service;

import com.targetindia.entity.Product;
import com.targetindia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public Product getProduct(Integer id) throws ServiceException {
        try{
            return repo.findById(id).orElse(null);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }


    public List<Product> getAllProducts() throws ServiceException{
        try{
            return repo.findAll();
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }
    public List<Product> getProductsBWPrice(double min,double max) throws ServiceException{
        try{
            return repo.findByUnitPriceBetween(min,max);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }
}

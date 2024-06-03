package com.targetindia.controller;

import com.targetindia.entity.CategoryList;
import com.targetindia.entity.Product;
import com.targetindia.entity.ProductList;
import com.targetindia.model.ErrorResponse;
import com.targetindia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handleGetOne(@PathVariable Integer id){
        Product product = service.getProduct(id);
        if(product==null){
            return ResponseEntity.status(404).body(new ErrorResponse("no data found"));
        }
        return ResponseEntity.ok(product);
    }
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handleGetAll(){
        return ResponseEntity.ok(new ProductList(service.getAllProducts()));
    }
    @GetMapping(path = "/price", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handleGet(@RequestParam( name="min") double min,@RequestParam (name="max") double max){
        return ResponseEntity.ok(new ProductList(service.getProductsBWPrice(min,max)));
    }

}

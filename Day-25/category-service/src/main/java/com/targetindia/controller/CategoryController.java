package com.targetindia.controller;

import com.targetindia.entity.Category;
import com.targetindia.entity.CategoryList;
import com.targetindia.model.ErrorResponse;
import com.targetindia.service.CategoryService;
import com.targetindia.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handleGetOne(@PathVariable Integer id){
        Category category = service.getCategory(id);
        if(category==null){
            return ResponseEntity.status(404).body(new ErrorResponse("no data found"));
        }
        return ResponseEntity.ok(category);
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handleGetAll(){
        return ResponseEntity.ok(new CategoryList(service.getAllCategories()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity handlePatch(@PathVariable Integer id, @RequestBody Category category) {

        try{
            Category updatedCategory = service.updateCategory(id,category);
            return ResponseEntity.ok(updatedCategory);
        }
        catch (ServiceException e){
            Map<String, Object> record = new LinkedHashMap<>();
            record.put("error", e.getMessage());
            record.put("Category", category);
            return ResponseEntity.ok(record);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity handlePut(@PathVariable Integer id, @RequestBody Category category) {


        try{
            Category updatedCategory = service.updateWholeCategory(id,category);
            return ResponseEntity.ok(updatedCategory);
        }
        catch (ServiceException e){
            Map<String, Object> record = new LinkedHashMap<>();
            record.put("error", e.getMessage());
            record.put("Category", category);
            return ResponseEntity.ok(record);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity handlePost(@RequestBody Category category) throws URISyntaxException {


        try{
            Category newCategory = service.addNewCategory(category);
            return ResponseEntity.ok(newCategory);
        }
        catch (ServiceException e){
            Map<String, Object> record = new LinkedHashMap<>();
            record.put("error", e.getMessage());
            record.put("Category", category);
            return ResponseEntity.ok(record);

        }

    }
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity handleDeleteOne(@PathVariable Integer id) {
        try{
             List <Category> updatedCategories = service.deleteCategory(id);
            return ResponseEntity.ok(new CategoryList(updatedCategories));
        }
        catch (ServiceException e){
            Map<String, Object> err = new LinkedHashMap<>();
            err.put("message", "no Category data found for id " + id);
            err.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }

    }
}

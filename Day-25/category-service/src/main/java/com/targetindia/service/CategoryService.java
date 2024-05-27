package com.targetindia.service;

import com.targetindia.entity.Category;
import com.targetindia.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category getCategory(Integer id) throws ServiceException {
        try{
            return repo.findById(id).orElse(null);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }

    public List<Category> getAllCategories() throws ServiceException{
        try{
            return repo.findAll();
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }

    public Category updateCategory(Integer id, Category category) throws ServiceException
    {
        try{
            Category cat = repo.getOne(id);
            if(category.getName()!= null)
                cat.setName(category.getName());
            if(category.getDescription()!= null)
                cat.setDescription(category.getDescription());
            return repo.save(cat);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }

    public Category updateWholeCategory(Integer id, Category category) throws ServiceException
    {
        try{
            category.setId(id);
            return repo.save(category);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }
    public Category addNewCategory( Category category) throws ServiceException
    {
        try{
            return repo.save(category);
        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }
    public List<Category> deleteCategory( Integer id) throws ServiceException
    {
        try{
            Category category = repo.getOne(id);
            repo.delete(category);
            return repo.findAll();

        }
        catch (Exception ex){
            throw new ServiceException(ex);
        }
    }



}

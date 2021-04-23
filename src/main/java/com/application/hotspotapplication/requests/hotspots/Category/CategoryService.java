package com.application.hotspotapplication.requests.hotspots.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

  @Autowired
  private CategoryDAO dao;

  public Category create(String categoryName){
    Category category = new Category(categoryName);
    List<Category> existingCategories = dao.findByName(categoryName);

    if(!existingCategories.isEmpty()){
      return existingCategories.get(0);
    }

    dao.save(category);
    return category;
  }




}

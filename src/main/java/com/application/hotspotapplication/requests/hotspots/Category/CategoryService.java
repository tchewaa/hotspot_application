package com.application.hotspotapplication.requests.hotspots.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryDAO dao;

  public Category create(String categoryName){
    Category category = new Category(categoryName);
    dao.save(category);
    return category;
  }




}

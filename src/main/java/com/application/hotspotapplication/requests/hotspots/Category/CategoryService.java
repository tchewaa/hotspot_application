package com.application.hotspotapplication.requests.hotspots.Category;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryDAO dao;

  public Category create(String categoryName){
    Category category = new Category(categoryName);

    if(isExistingCategory(categoryName)){
      return dao.findByName(categoryName).get();
    }
    dao.save(category);
    return category;
  }

  @SneakyThrows
  public Long getCategoryIdByName(String categoryName){
    if(isExistingCategory(categoryName)){
      return dao.findByName(categoryName).get().getId();
    }
    throw new ApiRequestException("Could hotspots with category " + categoryName, HttpStatus.BAD_REQUEST);
  }

  private boolean isExistingCategory(String categoryName){
    return dao.findByName(categoryName).isPresent();
  }





}

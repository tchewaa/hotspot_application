package com.application.hotspotapplication.requests.hotspots.Category.v1;


import com.application.hotspotapplication.requests.hotspots.Category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotspot/v1/category/")
@Slf4j
public class CategoryControllerV1 {

  @Autowired
  private CategoryService categoryService;





}

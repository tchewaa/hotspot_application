package com.application.hotspotapplication.requests.hotspots.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);


}

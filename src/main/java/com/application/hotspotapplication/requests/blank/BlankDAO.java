package com.application.hotspotapplication.requests.blank;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlankDAO extends JpaRepository<Blank, Long> {
  
  @Modifying
  @Transactional
  @Query("UPDATE Blank SET description = :description WHERE id = :id")
  void updateBlank(@Param("description") String description, @Param("id")Long id);

  Blank findBlankById(Long blankID);

}

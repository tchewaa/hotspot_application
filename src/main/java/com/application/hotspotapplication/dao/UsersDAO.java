package com.application.hotspotapplication.dao;

import com.application.hotspotapplication.entity.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsersDAO extends JpaRepository<Users, Long> {

  Users findUsersById(Long id);


  @Query("SELECT * FROM Users u WHERE u.email = :username")
  Users findUsersByEmail(@Param("username") String username);
}

package com.application.hotspotapplication.requests.users;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersDAO extends JpaRepository<Users, Long> {

  @Modifying
  @Transactional
  @Query("UPDATE Users SET firstName = :firstName, lastName = :lastName, email = :email WHERE id = :id")
  void updateUser(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("id")Long id);

  @Modifying
  @Transactional
  @Query("UPDATE Users SET active = :activate WHERE id = :id")
  void activateUser(@Param("activate") boolean activate, @Param("id")Long id);

  Users findUsersById(Long id);

  Users findUserByEmail(String email);

}

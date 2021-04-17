package com.application.hotspotapplication.service;

import com.application.hotspotapplication.entity.Users;
import com.application.hotspotapplication.dao.UsersDAO;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Named
public class UsersService {

  @Autowired
  private UsersDAO dao;

  public String allUsers() {
    List<Users> usersList = dao.findAll();
    return usersList.stream()
        .map(user -> user.getFirstName() + " " + user.getLastName() + ", " + user.getEmail())
        .collect(Collectors.joining("\n"));
  }

  public Users findUserById(Long userId) {
    return dao.findUsersById(userId);
  }

  public void createUser(String email, String fname ,String lname) {
    Users existUser = dao.findUsersByEmail(email);
    if(existUser == null)
    {
      Users newUser = new Users();
      newUser.setEmail(email);
      newUser.setFirstName(fname);
      newUser.setLastName(lname);
      dao.save(newUser);
    }

  }

}

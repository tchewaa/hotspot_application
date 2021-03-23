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
    String result = usersList.stream().map(user -> user.getFirstName()).collect(Collectors.joining(",")); ;
    return result;
  }

  public Users findUserById(Long userId) {
    return dao.findUsersById(userId);
  }

  public void createUser(Users users) {
    dao.save(users);
  }

}

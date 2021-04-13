package com.application.hotspotapplication.requests.users;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
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
    Users user =  dao.findUsersById(userId);
    if (user == null) {
      throw new ApiRequestException("User not found", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public void createUser(Users users) {
    dao.save(users);
  }

}

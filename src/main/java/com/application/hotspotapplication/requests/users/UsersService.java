package com.application.hotspotapplication.requests.users;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

  @Autowired
  private UsersDAO dao;

  @SneakyThrows
  public void createUser(Users users) {
    Users exsitingUser = findUserByEmail(users.getEmail());
    if (exsitingUser == null) dao.save(users);
    else throw new ApiRequestException("Duplicate User!", HttpStatus.BAD_REQUEST);
  }

  @SneakyThrows
  public void updateUser(Users users) {
    Users exsitingUser = findUserById(users.getId());
    if (exsitingUser != null) dao.updateUser(users.getFirstName(), users.getLastName(), users.getEmail(), users.getId());
    else throw new ApiRequestException("User not found!", HttpStatus.NOT_FOUND);
  }

  @SneakyThrows
  public void activateUser(String email) {
    Users exsitingUser = findUserByEmail(email);
    if (exsitingUser != null) dao.activateUser(true, exsitingUser.getId());
    else throw new ApiRequestException("User not found!", HttpStatus.NOT_FOUND);
  }

  @SneakyThrows
  public void deActivateUser(String email) {
    Users exsitingUser = findUserByEmail(email);
    if (exsitingUser != null) dao.activateUser(false, exsitingUser.getId());
    else throw new ApiRequestException("User not found!", HttpStatus.NOT_FOUND);
  }

  @SneakyThrows
  public List<Users> allUsers() {
    return dao.findAll();
  }

  @SneakyThrows
  public Users findUserById(Long userId) {
    Users user =  dao.findUsersById(userId);
    if (user == null) {
      throw new ApiRequestException("User not found!", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  @SneakyThrows
  public Users findUserByEmail(String email) {
    Users user =  dao.findUserByEmail(email);
    if (user == null) {
      throw new ApiRequestException("User not found!", HttpStatus.NOT_FOUND);
    }
    return user;
  }


}

package com.application.hotspotapplication.requests.users;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

  @Autowired
  private UsersDAO dao;

  @SneakyThrows
  private void create(Users user){
    if (user.getId() == null) dao.save(user);
    else update(user);
  }

  @SneakyThrows
  private void update(Users user){
    if (user != null) dao.updateUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
  }

  @SneakyThrows
  public void createUser(Users users) {
    Users exsitingUser = dao.findUserByEmail(users.getEmail());
    if (exsitingUser == null) create(users);
    else throw new ApiRequestException("Duplicate User!", HttpStatus.BAD_REQUEST);
  }
  @SneakyThrows
  public Users verifyUser(Users users) {
    Users exsitingUser = dao.findUserByEmail(users.getEmail());
    if (exsitingUser == null) create(users);
    return exsitingUser;
  }
  @SneakyThrows
  public void updateUser(Users users) {
    Users exsitingUser = findUserById(users.getId());
    if (exsitingUser != null) update(users);
  }



  @SneakyThrows
  public void activateUser(String email) {
    Users exsitingUser = findUserByEmail(email);
    if (exsitingUser != null) dao.activateUser(true, exsitingUser.getId());
  }

  @SneakyThrows
  public void deActivateUser(String email) {
    Users exsitingUser = findUserByEmail(email);
    if (exsitingUser != null) dao.activateUser(false, exsitingUser.getId());
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
  public Users generateUser()
  {
    String userDetails = (SecurityContextHolder.getContext().getAuthentication().getPrincipal()).toString();
    if(userDetails != null) {
      Users loggedInUser = new Users();
      JSONObject jsonObject = new JSONObject(userDetails);
      loggedInUser.setEmail(jsonObject.getString("email").toString());
      loggedInUser.setFirstName(((jsonObject.getString("name")).toString()).split(" ", 2)[0]);
      loggedInUser.setLastName(((jsonObject.getString("name")).toString()).split(" ", 2)[1]);
      loggedInUser.setActive(Boolean.TRUE);
      return loggedInUser;
    }
    else{
      throw new ApiRequestException("Google did not return the authenticated user details", HttpStatus.NOT_FOUND);
    }
  }
  @SneakyThrows
  public Long getLoggedInUserId()
  {
    Users user = generateUser();
    if(user != null) {

      return user.getId();
    }
    else{
      throw new ApiRequestException("Google did not return the authenticated user details", HttpStatus.NOT_FOUND);
    }
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

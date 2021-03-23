package com.application.hotspotapplication.controller;


import static com.application.hotspotapplication.utils.Constants.PASSWORD;

import com.application.hotspotapplication.entity.Users;
import com.application.hotspotapplication.service.UsersService;
import com.application.hotspotapplication.utils.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotspotapp/v1")
@Slf4j
public class ApplicationControllerV1 {

  @Inject
  private UsersService usersService;

  //TODO: FIX API Statuses and Error Handling
  @RequestMapping(value = "/checkHealth", method = RequestMethod.GET)
  public String checkHealth() {
    Connection c = null;
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      c = DriverManager.getConnection(Constants.URL, Constants.USER, PASSWORD);
      return "UP";
    } catch (Exception e) {
      log.error(e.getMessage());
      return "DOWN";
    }
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  public String findUserById(
      @PathVariable(name = "id", required = true) Long userId) {
    Users users = (Users) usersService.findUserById(userId);
    if (users != null){
      return users.toString();
    }else{
      return "No User Found by that id";
    }
  }

  @RequestMapping(value = "/user/all", method = RequestMethod.GET)
  public String allUsers() {
    String result = usersService.allUsers();
    return result;
  }

  @PostMapping(path = "/user/create", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public void createUser(@RequestBody Users user) {
    usersService.createUser(user);
  }


}

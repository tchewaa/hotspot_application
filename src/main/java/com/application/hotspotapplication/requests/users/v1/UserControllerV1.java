package com.application.hotspotapplication.requests.users.v1;


import com.application.hotspotapplication.requests.users.Users;
import com.application.hotspotapplication.requests.users.UsersService;
import com.application.hotspotapplication.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.application.hotspotapplication.utils.Constants.PASSWORD;

@RestController
@RequestMapping("users/v1")
@Slf4j
public class UserControllerV1 {

  @Inject
  private UsersService usersService;

  //TODO: FIX API Statuses and Error Handling
  @GetMapping(value = "/checkHealth")
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

  @GetMapping(value = "/{id}")
  public ResponseEntity findUserById(
      @PathVariable(name = "id", required = true) Long userId) {
    Users users = (Users) usersService.findUserById(userId);
    return  new ResponseEntity<Users>(users, HttpStatus.OK);
  }

  @GetMapping(value = "/all")
  public String allUsers() {
    String result = usersService.allUsers();
    return result;
  }

  @PostMapping(path = "/create", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public void createUser(@RequestBody Users user) {
    usersService.createUser(user);
  }


}

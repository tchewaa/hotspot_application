package com.application.hotspotapplication.requests.users.v1;


import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.requests.users.Users;
import com.application.hotspotapplication.requests.users.UsersService;
import com.application.hotspotapplication.utils.Constants;
import java.util.List;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotspot/v1/user/")
@Slf4j
public class UserControllerV1 {

  @Inject
  private UsersService usersService;

  @PostMapping(path = "/create", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public ResponseEntity createUser(@RequestBody Users user) {
    try {
      usersService.createUser(user);
      return new ResponseEntity(HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }

  @PostMapping(path = "/update", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public ResponseEntity updateUser(@RequestBody Users user) {
    try {
      usersService.updateUser(user);
      return new ResponseEntity(HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }

  @PostMapping(path = "/{email}/activate", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public ResponseEntity activateUser(@PathVariable(name = "email", required = true) String email) {
    try {
      usersService.activateUser(email);
      return new ResponseEntity(HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }

  @PostMapping(path = "/{email}/deactivate", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public ResponseEntity deActivateUser(@PathVariable(name = "email", required = true) String email) {
    try {
      usersService.deActivateUser(email);
      return new ResponseEntity<Users>(HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }


  @GetMapping(value = "/all")
  public ResponseEntity allUsers() {
    List<Users> users =   usersService.allUsers();
    return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
  }

  @GetMapping(value = "/id/{id}")
  public ResponseEntity findUserById(
      @PathVariable(name = "id", required = true) Long userId) {
    try {
      Users users = (Users) usersService.findUserById(userId);
      return new ResponseEntity<Users>(users, HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }

  @GetMapping(value = "/email/{email}")
  public ResponseEntity findUserById(
      @PathVariable(name = "email", required = true) String email) {
    try {
      Users users = (Users) usersService.findUserByEmail(email);
      return new ResponseEntity<Users>(users, HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }



}

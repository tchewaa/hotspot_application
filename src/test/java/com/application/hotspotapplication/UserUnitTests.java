package com.application.hotspotapplication;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.requests.users.Users;
import com.application.hotspotapplication.requests.users.UsersService;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserUnitTests {

  @Inject
  UsersService usersService;

  @Test
  void activateUser() {
    try {
      usersService.activateUser("user@a.com");
    } catch (ApiRequestException e) {
      log.error("Unable to initialise resources\nmessage:" + e.getMessage());
    }
  }

  @Test
  void deactivateUser() {
    try {
      usersService.deActivateUser("user@a.com");
    } catch (ApiRequestException e) {
      log.error("Unable to initialise resources\nmessage:" + e.getMessage());
    }
  }

  @Test
  void findByEmail() {
    try {
      Users user = usersService.findUserByEmail("user@a.com");
      Assert.assertTrue(user != null);
    } catch (ApiRequestException e) {
      log.error("Unable to initialise resources\nmessage:" + e.getMessage());
    }
  }

  @Test
  void findByWrongEmail() {
    try {
      Users user = usersService.findUserByEmail("wrongEmail@a.com");
      Assert.assertTrue(user == null);
    } catch (ApiRequestException e) {
      log.error("Unable to initialise resources\nmessage:" + e.getMessage());
    }
  }

  @Test
  void findById() {
    try {
      Users user = usersService.findUserById(1l);
      Assert.assertTrue(user != null);
    } catch (ApiRequestException e) {
      log.error("Unable to initialise resources\nmessage:" + e.getMessage());
    }
  }

  @Test
  void findByWrongId() {
    try {
      Users user = usersService.findUserById(111l);
      Assert.assertTrue(user == null);
    } catch (ApiRequestException e) {
      log.error("Unable to initialise resources\nmessage:" + e.getMessage());
    }
  }


}

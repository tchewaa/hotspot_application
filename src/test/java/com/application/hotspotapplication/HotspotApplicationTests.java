package com.application.hotspotapplication;


import com.application.hotspotapplication.requests.users.UsersService;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class HotspotApplicationTests {

  @Inject
  private UsersService usersService;

  @Test
  public void allUsers(){
    log.info(usersService.allUsers().toString());
  }

}

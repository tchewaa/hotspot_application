package com.application.hotspotapplication.requests.hotspots.v1;


import static com.application.hotspotapplication.utils.Constants.PASSWORD;

import com.application.hotspotapplication.requests.hotspots.Location;
import com.application.hotspotapplication.requests.hotspots.LocationService;
import com.application.hotspotapplication.utils.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
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
@RequestMapping("hotspots/v1")
@Slf4j
public class HotSpotControllerV1 {

  @Inject
  private LocationService locationService;



}

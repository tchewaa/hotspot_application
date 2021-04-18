package com.application.hotspotapplication.requests.hotspots.v1;


import com.application.hotspotapplication.requests.hotspots.LocationService;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotspot/v1")
@Slf4j
public class HotSpotControllerV1 {

  @Inject
  private LocationService locationService;



}

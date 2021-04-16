package com.application.hotspotapplication.requests.hotspots.v1;


import com.application.hotspotapplication.requests.hotspots.Location;
import com.application.hotspotapplication.requests.hotspots.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotspots/v1")
@Slf4j
public class HotSpotControllerV1 {

  @Autowired
  private LocationService locationService;

  @PutMapping("/create")
  public Location createHotspotReport(@RequestParam String streetAddress,@RequestParam String areaName,
                                      @RequestParam String cityName,@RequestParam int postalCode,
                                      @RequestParam int categoryId ){
    Location location = locationService.createHotspotReport(streetAddress, areaName,cityName,postalCode, categoryId);
    return location;
  }


}

package com.application.hotspotapplication.requests.hotspots.Location.v1;


import com.application.hotspotapplication.requests.hotspots.Location.Location;
import com.application.hotspotapplication.requests.hotspots.Location.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations/v1")
@Slf4j
public class LocationControllerV1 {

  @Autowired
  private LocationService locationService;

  @PutMapping("/create")
  public Location createHotspotReport(@RequestParam String streetAddress,
                                      @RequestParam String areaName,
                                      @RequestParam String cityName,
                                      @RequestParam int postalCode){
    Location location =
            locationService.createHotspotReport(streetAddress, areaName,cityName,postalCode);
    return location;
  }

  @GetMapping("/neighbourhood")
  public List<Location> getLocationsByNeighbourhood(@RequestParam(name = "neighbourhood", required = true) String neighbourhood){
    return locationService.getHotspotsByNeighbourhood(neighbourhood);
  }

  @GetMapping("/region")
  public List<Location> getLocationsByRegion(@RequestParam(name = "region", required = true) String region){
      return locationService.getHotspotsByRegion(region);
  }

  @GetMapping("/all")
  public List<Location> getAllHotSpots(){
    return locationService.getAllHotSpots();
  }

}

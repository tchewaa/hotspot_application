package com.application.hotspotapplication.requests.hotspots;

import com.application.hotspotapplication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class LocationService {
  @Autowired
  private LocationDAO dao;
  @Value("${api.key}")
  private String positionStackApiKey;
  private HashMap<String, List<Vector2>> provinceHotspot; //basically just for easy lookup - have province as key and list of hotspots in the province as values
  private final RestTemplate restTemplate;
  
  public LocationService(RestTemplateBuilder restTemplateBuilder){
    this.restTemplate = restTemplateBuilder.build();
  }

  public Location createHotspotReport(String streetAddress, String areaName, String cityName,int postalCode, int category){
    String addressQuery = streetAddress + ", " + areaName + ", " + cityName + ", " + postalCode;
    Location curLocation = getHotspotLocation(addressQuery, category);
    if(curLocation != null){
      curLocation.setDate(new Date(System.currentTimeMillis()));
      dao.save(curLocation);
    }

    return curLocation;
  }

  private Location getHotspotLocation(String addressQuery, int category){
    Location curLocation = null;

    String requestURL = Constants.POSSITIONSTACK_API_URL_FORWARD + positionStackApiKey + "&query=" + addressQuery;
    ResponseEntity<LocationData> responseEntity =  restTemplate.getForEntity(requestURL, LocationData.class);

    if(responseEntity.getStatusCode() == HttpStatus.OK) {
      curLocation = responseEntity.getBody().getData().get(0);
      curLocation.setCategory(category);
    }
    return curLocation;
  }


}

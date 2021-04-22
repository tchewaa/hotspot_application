package com.application.hotspotapplication.requests.hotspots.Location;

import com.application.hotspotapplication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
  @Autowired
  private LocationDAO dao;
  @Value("${api.key}")
  private String positionStackApiKey;
  private final RestTemplate restTemplate;

  public LocationService(RestTemplateBuilder restTemplateBuilder){
    this.restTemplate = restTemplateBuilder.build();
  }

  public Location createHotspotReport(String streetAddress, String areaName, String cityName,int postalCode){
    String addressQuery = streetAddress + ", " + areaName + ", " + cityName + ", " + postalCode;
    Location curLocation = getHotspotLocation(addressQuery);
    if(!dao.findByLatitudeAndLongitude(curLocation.getLatitude(), curLocation.getLongitude()).isEmpty()){
      return dao.findByLatitudeAndLongitude(curLocation.getLatitude(), curLocation.getLongitude()).get(0);
    }
    if(curLocation.getConfidence() >= 0.5){ //@TODO find a better more accurate way of determining if location is correct.
      curLocation.setStreetAddress(streetAddress);
      curLocation.setCity(cityName);
      curLocation.setNeighbourhood(areaName);
      curLocation.setPostalCode(postalCode);
      dao.save(curLocation);
    }
    return curLocation;
  }

  public List<Location> getHotspotsByNeighbourhood(String neighbourhood) {return dao.findByNeighbourhood(neighbourhood);}

  public List<Location> getHotspotsByRegion(String region){
    return dao.findByRegion(region);
  }

  public List<Location> getAllHotSpots(){
    return dao.findAll();
  }

  private Location getHotspotLocation(String addressQuery){
    Location curLocation = null;

    String requestURL = Constants.POSSITIONSTACK_API_URL_FORWARD + positionStackApiKey + "&query=" + addressQuery + "&country=ZA";
    ResponseEntity<LocationData> responseEntity =  restTemplate.getForEntity(requestURL, LocationData.class);

    if(responseEntity.getStatusCode() == HttpStatus.OK) {
      curLocation = getConfidentLocation(responseEntity.getBody().getData());
    }
    return curLocation;
  }

  public Optional<Location> findLocationById(Long id){
    return dao.findById(id);
  }


  private Location getConfidentLocation(List<Location> locations){
    return locations.stream().max(Comparator.comparing(Location::getConfidence)).get();

  }



}

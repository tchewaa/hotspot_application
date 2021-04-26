package com.application.hotspotapplication.requests.hotspots.Location;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationService {

  @Autowired
  private LocationDAO dao;
  @Value("${api.key}")
  private String positionStackApiKey;
  private final RestTemplate restTemplate;

  public LocationService(RestTemplateBuilder restTemplateBuilder){
    this.restTemplate = restTemplateBuilder.build();
  }

  @SneakyThrows
  public Location createHotspotLocation(String streetAddress, String areaName, String cityName, int postalCode){
    String addressQuery = streetAddress + ", " + areaName + ", " + cityName + ", " + postalCode;
    try {
      Location curLocation = fetchLocationFromApi(addressQuery).get(Constants.TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);

      if (locationExists(curLocation.getLatitude(), curLocation.getLongitude())) {
        return dao.findByLatitudeAndLongitude(curLocation.getLatitude(), curLocation.getLongitude()).get();
      }

      if (curLocation.getConfidence() >= 0.5) {
        curLocation.setLocationData(streetAddress, cityName, areaName, postalCode);
        dao.save(curLocation);
        return curLocation;
      }

      throw new ApiRequestException("Could not find specified location", HttpStatus.BAD_REQUEST);
    }catch (Exception e){
      throw new ApiRequestException("Could not add specified location", HttpStatus.BAD_REQUEST);
    }
  }
  public boolean locationExists(Double latitude, Double longitude){
    return dao.findByLatitudeAndLongitude(latitude, longitude).isPresent();
  }

  @SneakyThrows
  public List<Location> getLocationsByStreetName(String streetAddress){
    List<Location> locations = dao.findByStreetAddressContaining(streetAddress);
    return sendLocations(locations,"Could not find locations with street address " + streetAddress);
  }


  @SneakyThrows
  public List<Location> getLocationsByNeighbourhood(String neighbourhood) {
    List<Location> locations = dao.findByNeighbourhood(neighbourhood);
    return sendLocations(locations, "Could not find locations at " + neighbourhood);
  }

  @SneakyThrows
  public List<Location> getLocationsByRegion(String region) {
    List<Location> locations = dao.findByRegion(region);
    return sendLocations(locations, "Could not find locations in region " + region);
  }

  @SneakyThrows
  public List<Location> getLocations() {
    List<Location> locations = dao.findAll();
    return sendLocations(locations, "No locations currently");
  }

  @SneakyThrows
  public List<Location> getLocationsByNeighbourhoodAndRegion(String neighbourhood, String region) {
    List<Location> locations = dao.findByNeighbourhoodAndRegion(neighbourhood, region);
    return sendLocations(locations, "Could not find any locations at " + neighbourhood + " in " + region);
  }

  @SneakyThrows
  private List<Location> sendLocations(List<Location> locations, String apiExceptionMessage){
    if(!locations.isEmpty()){
      return locations;
    }
    throw new ApiRequestException(apiExceptionMessage, HttpStatus.BAD_REQUEST);
  }

  @SneakyThrows
  @Async
  private CompletableFuture<Location> fetchLocationFromApi(String addressQuery){
    String requestURL = Constants.POSSITIONSTACK_API_URL_FORWARD + positionStackApiKey + "&query=" + addressQuery + "&country=ZA";
    ResponseEntity<LocationData> responseEntity =  restTemplate.getForEntity(requestURL, LocationData.class);

    if(responseEntity.getStatusCode() == HttpStatus.OK) {
      Location curLocation = getConfidentLocation(responseEntity.getBody().getData());
      return CompletableFuture.completedFuture(curLocation);
    }
    throw new ApiRequestException("Cannot find location", HttpStatus.BAD_REQUEST);
  }

  public Optional<Location> findLocationById(Long id){
    return dao.findById(id);
  }


  private Location getConfidentLocation(List<Location> locations){
    return locations.stream().max(Comparator.comparing(Location::getConfidence)).get();
  }

}

package com.application.hotspotapplication.requests.hotspots.HotspotLocation;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.requests.hotspots.Category.Category;
import com.application.hotspotapplication.requests.hotspots.Category.CategoryService;
import com.application.hotspotapplication.requests.hotspots.Location.Location;
import com.application.hotspotapplication.requests.hotspots.Location.LocationService;
import com.application.hotspotapplication.requests.users.UsersService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotService {

    @Autowired
    private HotspotDAO dao;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UsersService usersService;

    public Hotspot create(String address,String areaName, String cityName,int postalCode, String categoryName){
        Location location = locationService.createHotspotLocation(address, areaName, cityName, postalCode);
        Category category = categoryService.create(categoryName);
        Hotspot newHotspot = new Hotspot(location, category);
        return newHotspot;
    }

    public void saveHotspot(Hotspot newHotspot){
        boolean exists = dao.findAll().stream().anyMatch(hotspot -> hotspot.getId().getCategoryId().equals(newHotspot.getCategory().getId()) && hotspot.getId().getLocationId().equals(newHotspot.getLocation().getId()));
        if(exists){
            incrementExistingHotspotReport(newHotspot.getLocation().getId(), newHotspot.getCategory().getId());
            return;
        }
        newHotspot.addReport();
        dao.save(newHotspot);
    }

    @SneakyThrows
    public Optional<Hotspot> deleteHotspotEntry(Long locationId, Long categoryId){
        Optional<Hotspot> optionalHotspotToDel = dao.findById(new HotspotId(locationId, categoryId));
        if(optionalHotspotToDel.isPresent()){
            if(optionalHotspotToDel.get().getNumReports() > 1){
                decrementExistingHotspotReport(locationId, categoryId);
                return optionalHotspotToDel;
            }
            dao.delete(optionalHotspotToDel.get());
            return optionalHotspotToDel;
        }
        throw new ApiRequestException("Could not find hotspot to delete", HttpStatus.BAD_REQUEST);
    }

    private void decrementExistingHotspotReport(Long locationId, Long categoryId){
        dao.decrementHotspotReport(locationId, categoryId);
    }

    private void incrementExistingHotspotReport(Long locationId, Long categoryId) {
        dao.incrementHotspotReport(locationId, categoryId);
    }
    @SneakyThrows
    public List<Hotspot> getHotspotsByStreetAddress(String streetAddress){
        List<Hotspot> hotspots = new ArrayList<>();
        List<Location> locations = locationService.getLocationsByStreetName(streetAddress);
        locations.forEach(location -> hotspots.addAll(location.getHotspots()));
        return hotspots;
    }
    @SneakyThrows
    public List<Hotspot> getHotspotsByRegion(String region){
        List<Location> regionLocations =  locationService.getLocationsByRegion(region);
        List<Hotspot> hotspots = new ArrayList<>();
        regionLocations.forEach(location -> hotspots.addAll(location.getHotspots()));
        return hotspots;
    }
    @SneakyThrows
    public List<Hotspot> getHotspotByNeighbourhood(String neighbourhood){
        List<Location> locations = locationService.getLocationsByNeighbourhood(neighbourhood);
        List<Hotspot> hotspots = new ArrayList<>();
        locations.forEach(location -> hotspots.addAll(location.getHotspots()));
        return hotspots;
    }
    @SneakyThrows
    public List<Hotspot> getHotspotByNeighbourhoodAndRegion(String neighbourhood, String region){
        List<Location> locations = locationService.getLocationsByNeighbourhoodAndRegion(neighbourhood, region);
        List<Hotspot> hotspots = new ArrayList<>();
        locations.forEach(location -> hotspots.addAll(dao.findAllByLocationId(location.getId())));
        return hotspots;
    }
    @SneakyThrows
    public List<Hotspot> getAll(){
        List<Hotspot> allHotspots = dao.findAll();
        if(!allHotspots.isEmpty()){
            return allHotspots;
        }
        throw new ApiRequestException("No hotspots reported", HttpStatus.BAD_REQUEST);
    }

}

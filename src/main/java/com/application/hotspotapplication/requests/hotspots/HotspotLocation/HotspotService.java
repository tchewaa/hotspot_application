package com.application.hotspotapplication.requests.hotspots.HotspotLocation;

import com.application.hotspotapplication.requests.hotspots.Category.Category;
import com.application.hotspotapplication.requests.hotspots.Category.CategoryService;
import com.application.hotspotapplication.requests.hotspots.Location.Location;
import com.application.hotspotapplication.requests.hotspots.Location.LocationService;
import com.application.hotspotapplication.requests.users.UsersService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Hotspot> deleteHotspotEntry(Long locationId, Long categoryId){
        Optional<Hotspot> optionalHotspotToDel = dao.findById(new HotspotId(locationId, categoryId));
        if(optionalHotspotToDel.isPresent()){
            if(optionalHotspotToDel.get().getNumReports() > 1){
                decrementExistingHotspotReport(locationId, categoryId);
                return optionalHotspotToDel;
            }
            dao.delete(optionalHotspotToDel.get());
        }
        return optionalHotspotToDel;
    }

    private void decrementExistingHotspotReport(Long locationId, Long categoryId){
        Optional<Hotspot> optionalHotspotToDecrement =  dao.findAll().stream().filter(hotspot ->  hotspot.getId().getCategoryId().equals(categoryId) && hotspot.getId().getLocationId().equals(locationId)).findFirst();

        if(optionalHotspotToDecrement.isPresent()){
            optionalHotspotToDecrement.get().deleteReport();
            dao.save(optionalHotspotToDecrement.get());
        }
    }

    private void incrementExistingHotspotReport(Long locationId, Long categoryId) {
        Optional<Hotspot> optionalToIncrement =  dao.findAll().stream().filter(hotspot ->  hotspot.getId().getCategoryId().equals(categoryId) && hotspot.getId().getLocationId().equals(locationId)).findFirst();

        if(optionalToIncrement.isPresent()){
            optionalToIncrement.get().addReport();
            dao.save(optionalToIncrement.get());
        }
    }

    public List<Hotspot> getHotspotsByStreetName(String streetName){
        List<Hotspot> hotspots = new ArrayList<>();
        List<Location> locations =
                locationService.
                        getAllHotspotLocations().
                        stream().
                        filter(location -> location.getStreetAddress().matches(".*" + streetName +".*")). //Tries to get a street name that matches the name that was passed in
                        collect(Collectors.toList());
        locations.forEach(location -> hotspots.addAll(location.getHotspots()));
        return hotspots;
    }

    public List<Hotspot> getHotspotsByRegion(String region){
        List<Location> regionLocations =  locationService.getHotspotLocationsByRegion(region);
        List<Hotspot> hotspots = new ArrayList<>();

        regionLocations.forEach(location -> hotspots.addAll(location.getHotspots()));

        return hotspots;
    }

    public List<Hotspot> getHotspotByNeighbourhood(String neighbourhood){
        List<Hotspot> hotspots = new ArrayList<>();
        List<Location> locations = locationService.getHotspotLocationByNeighbourhood(neighbourhood);
        locations.forEach(location -> hotspots.addAll(location.getHotspots()));
        return hotspots;
    }


    public void updateUserHotspot(){

    }

    public List<Hotspot> getAll(){
        return dao.findAll();
    }

}

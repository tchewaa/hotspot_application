package com.application.hotspotapplication.requests.hotspots.HotspotLocation;

import com.application.hotspotapplication.requests.hotspots.Category.Category;
import com.application.hotspotapplication.requests.hotspots.Category.CategoryService;
import com.application.hotspotapplication.requests.hotspots.Location.Location;
import com.application.hotspotapplication.requests.hotspots.Location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotspotService {
    @Autowired
    private HotspotDAO dao;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CategoryService categoryService;
    public Hotspot create(String address,String areaName, String cityName,int postalCode, String categoryName){
        Location location = locationService.createHotspotReport(address, areaName, cityName, postalCode);
        Category category = categoryService.create(categoryName);
        Hotspot newHotspot = new Hotspot(location, category);

        boolean exists = dao.findAll().stream().anyMatch(hotspot -> hotspot.id.getCategoryId().equals(category.getId()) && hotspot.id.getLocationId().equals(location.getId()));
        if(exists){
            return incrementExistingHotspotReport(location.getId(), category.getId());
        }
        newHotspot.addReport();
        dao.save(newHotspot);
        return newHotspot;
    }

    private Hotspot incrementExistingHotspotReport(Long locationId, Long categoryId) {
        Optional<Hotspot> cur =  dao.findAll().stream().filter(hotspot ->  hotspot.id.getCategoryId().equals(categoryId) && hotspot.id.getLocationId().equals(locationId)).findFirst();

        if(cur.isPresent()){
            cur.get().addReport();
            dao.save(cur.get());
            return cur.get();
        }
        return null;
    }

    public List<Hotspot> getAll(){
        return dao.findAll();
    }

}

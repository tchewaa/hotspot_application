package com.application.hotspotapplication.requests.hotspots.HotspotLocation;

import com.application.hotspotapplication.requests.hotspots.Category.Category;
import com.application.hotspotapplication.requests.hotspots.Category.CategoryService;
import com.application.hotspotapplication.requests.hotspots.Location.Location;
import com.application.hotspotapplication.requests.hotspots.Location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Hotspot hotspot = new Hotspot(location, category); //@TODO need to check if hotspot has unique address & category in order to increment num reports
        dao.save(hotspot);
        return hotspot;
    }

    public List<Hotspot> getAll(){
        return dao.findAll();
    }

}

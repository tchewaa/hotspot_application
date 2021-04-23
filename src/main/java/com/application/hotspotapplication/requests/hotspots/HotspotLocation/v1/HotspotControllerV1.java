package com.application.hotspotapplication.requests.hotspots.HotspotLocation.v1;

import com.application.hotspotapplication.requests.hotspots.HotspotLocation.Hotspot;
import com.application.hotspotapplication.requests.hotspots.HotspotLocation.HotspotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hotspot/v1/hotspots")
@Slf4j
public class HotspotControllerV1 {

    @Autowired
    private HotspotService hotspotService;


    @PutMapping("/create")
    public Hotspot create(
            @RequestParam String streetAddress,
            @RequestParam String areaName,
            @RequestParam String cityName,
            @RequestParam String categoryName,
            @RequestParam int postalCode
    ){
        return hotspotService.create(streetAddress, areaName, cityName,postalCode, categoryName);
    }

    @GetMapping("/all")
    public List<Hotspot> getAll(){
        return hotspotService.getAll();
    }

    @GetMapping("/region/{region}")
    public List<Hotspot> getHotspotsByRegion(@PathVariable(name = "region", required = true) String region){

        return hotspotService.getHotspotsByRegion(region);
    }

    @GetMapping("/neighbourhood/{neighbourhood}")
    public List<Hotspot> getHotspotsByNeighbourhood(@PathVariable(name = "neighbourhood", required = true) String neighbourhood){
        return hotspotService.getHotspotByNeighbourhood(neighbourhood);
    }

    @GetMapping("streetName/{streetName}")
    public List<Hotspot> getHotspotsByStreetName(@PathVariable(name="streetName", required = true) String streetName){
        return hotspotService.getHotspotsByStreetName(streetName);
    }


}

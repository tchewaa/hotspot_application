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

    @GetMapping("/all")
    public List<Hotspot> getAll(){
        return hotspotService.getAll();
    }

    @GetMapping("/region/{region}")
    public List<Hotspot> getHotspotsByRegion(@PathVariable(name = "region") String region){

        return hotspotService.getHotspotsByRegion(region);
    }

    @GetMapping("/neighbourhood/{neighbourhood}")
    public List<Hotspot> getHotspotsByNeighbourhood(@PathVariable(name = "neighbourhood") String neighbourhood){
        return hotspotService.getHotspotByNeighbourhood(neighbourhood);
    }

    @GetMapping("streetAddress/{streetAddress}")
    public List<Hotspot> getHotspotsByStreetName(@PathVariable(name="streetAddress") String streetAddress){
        return hotspotService.getHotspotsByStreetAddress(streetAddress);
    }

    @GetMapping("/neighbourhood/{neighbourhood}/region/{region}")
    public List<Hotspot> getHotspotsByNeighbourhoodAndRegion(@PathVariable(name = "neighbourhood") String neighbourhood, @PathVariable(name = "region") String region){
        return hotspotService.getHotspotByNeighbourhoodAndRegion(neighbourhood, region);
    }



}

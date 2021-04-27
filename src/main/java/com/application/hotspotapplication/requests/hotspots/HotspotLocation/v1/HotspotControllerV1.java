package com.application.hotspotapplication.requests.hotspots.HotspotLocation.v1;

import com.application.hotspotapplication.requests.hotspots.HotspotLocation.Hotspot;
import com.application.hotspotapplication.requests.hotspots.HotspotLocation.HotspotService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hotspot/v1/hotspots")
@Slf4j
public class HotspotControllerV1 {

    @Autowired
    private HotspotService hotspotService;

    @SneakyThrows
    @GetMapping("/all")
    public List<Hotspot> getAll(){
        return hotspotService.getAll();
    }
    @SneakyThrows
    @GetMapping("/region/{region}")
    public List<Hotspot> getHotspotsByRegion(@PathVariable(name = "region") String region){

        return hotspotService.getHotspotsByRegion(region);
    }
    @SneakyThrows
    @GetMapping("/neighbourhood/{neighbourhood}")
    public List<Hotspot> getHotspotsByNeighbourhood(@PathVariable(name = "neighbourhood") String neighbourhood){
        return hotspotService.getHotspotByNeighbourhood(neighbourhood);
    }
    @SneakyThrows
    @GetMapping("streetAddress/{streetAddress}")
    public List<Hotspot> getHotspotsByStreetName(@PathVariable(name="streetAddress") String streetAddress){
        return hotspotService.getHotspotsByStreetAddress(streetAddress);
    }
    @SneakyThrows
    @GetMapping("/neighbourhood/{neighbourhood}/region/{region}")
    public List<Hotspot> getHotspotsByNeighbourhoodAndRegion(@PathVariable(name = "neighbourhood") String neighbourhood, @PathVariable(name = "region") String region){
        return hotspotService.getHotspotByNeighbourhoodAndRegion(neighbourhood, region);
    }
    @SneakyThrows
    @GetMapping("/category/{category}")
    public List<Hotspot> getHotspotsByCategory(@PathVariable(name = "category") String categoryName){
        return hotspotService.getHotspotsByCategory(categoryName);
    }
}

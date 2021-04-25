package com.application.hotspotapplication.requests.hotspots.UsersHotspots.v1;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.requests.hotspots.UsersHotspots.UsersHotspots;
import com.application.hotspotapplication.requests.hotspots.UsersHotspots.UsersHotspotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hotspot/v1/users")
public class UsersHotspotsControllerV1 {

    @Autowired
    private UsersHotspotsService usersHotspotsService;

    @PutMapping("/create")
    public UsersHotspots create(
            @RequestParam String streetAddress,
            @RequestParam String areaName,
            @RequestParam String cityName,
            @RequestParam String categoryName,
            @RequestParam int postalCode
    ){
        return usersHotspotsService.createHotspot(streetAddress, areaName, cityName,postalCode, categoryName);
    }

    @GetMapping("/get")
    public List<UsersHotspots> getUsersHotspots(){
        return usersHotspotsService.getUserHotspots();
    }

    @DeleteMapping("/delete/location/{locationId}/category/{categoryId}")
    public ResponseEntity deleteUserHotspot(@PathVariable(name = "locationId") Long locationId, @PathVariable(name = "categoryId") Long categoryId){
        Optional<UsersHotspots> deletedHotspot = usersHotspotsService.deleteUserHotspots(locationId, categoryId);
        try {
            if (deletedHotspot.isPresent()) {
                return new ResponseEntity<UsersHotspots>(deletedHotspot.get(), HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (ApiRequestException e) {
            throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
        }
    }
}

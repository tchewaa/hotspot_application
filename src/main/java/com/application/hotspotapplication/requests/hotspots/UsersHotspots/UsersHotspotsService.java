package com.application.hotspotapplication.requests.hotspots.UsersHotspots;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.requests.hotspots.Category.CategoryService;
import com.application.hotspotapplication.requests.hotspots.HotspotLocation.Hotspot;
import com.application.hotspotapplication.requests.hotspots.HotspotLocation.HotspotService;
import com.application.hotspotapplication.requests.hotspots.Location.LocationService;
import com.application.hotspotapplication.requests.users.Users;
import com.application.hotspotapplication.requests.users.UsersService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersHotspotsService {
    String email = "user@2.com"; //@TODO DELETE  -> just for testing purposes while OAuth gets sorted

    @Autowired
    private UsersHotspotsDAO dao;
    @Autowired
    private HotspotService hotspotService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UsersService usersService;

    @SneakyThrows
    public UsersHotspots createHotspot(String address,String areaName, String cityName,int postalCode, String categoryName){
        Hotspot hotspot = hotspotService.create(address, areaName, cityName, postalCode, categoryName);
        Users user = getLoggedUser();
        UsersHotspots usersHotspots = new UsersHotspots(hotspot.getLocation(), hotspot.getCategory(), user);
        if(!userHotspotAlreadyExists(hotspot.getLocation().getId(), hotspot.getCategory().getId(), user.getId())){
            dao.save(usersHotspots);
            hotspotService.saveHotspot(hotspot);
            return usersHotspots;
        }
        throw new ApiRequestException("Cannot add duplicate hotspots for this user", HttpStatus.BAD_REQUEST);
    }

    private boolean userHotspotAlreadyExists(Long locationId, Long categoryId, Long userId){
        return dao.findByLocationIdAndCategoryIdAndUserId(locationId, categoryId, userId).isPresent();
    }

    @SneakyThrows
    public List<UsersHotspots> getUserHotspots(){
        List<UsersHotspots> usersHotspots = getLoggedUser().getUsersHotspots();
        if(!usersHotspots.isEmpty()){
            return  usersHotspots;
        }
        throw new ApiRequestException("User has created no hotspots", HttpStatus.OK);
    }

    @SneakyThrows
    public Optional<UsersHotspots> deleteUserHotspots(Long locationId, Long categoryId){
        Optional<UsersHotspots> optionalUsersHotspotsToDelete = dao.findByLocationIdAndCategoryIdAndUserId(locationId, categoryId, getLoggedUser().getId());

        if(optionalUsersHotspotsToDelete.isPresent()) {
            hotspotService.deleteHotspotEntry(locationId, categoryId);
            dao.delete(optionalUsersHotspotsToDelete.get());
            return optionalUsersHotspotsToDelete;
        }
        throw new ApiRequestException("Hotspot does not exist", HttpStatus.BAD_REQUEST);
    }

    private Users getLoggedUser() {
        return usersService.findUserByEmail(email);
    }



}

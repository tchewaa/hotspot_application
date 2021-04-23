package com.application.hotspotapplication.requests.hotspots.Location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationDAO extends JpaRepository<Location, Long> {
    List<Location> findByRegion(String region);
    List<Location> findByCity(String city);
    List<Location> findByNeighbourhood(String neighbourhood);
    List<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
}

package com.application.hotspotapplication.requests.hotspots.Location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationDAO extends JpaRepository<Location, Long> {
    List<Location> findByRegion(String region);
    List<Location> findByCity(String city);
    List<Location> findByNeighbourhood(String neighbourhood);
    Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
}

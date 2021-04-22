package com.application.hotspotapplication.requests.hotspots.HotspotLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotspotDAO extends JpaRepository<Hotspot, HotspotId> {
    List<Hotspot> findAllByLocationId(Long locationId);
}

package com.application.hotspotapplication.requests.hotspots.HotspotLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HotspotDAO extends JpaRepository<Hotspot, HotspotId> {
    List<Hotspot> findAllByLocationId(Long locationId);

    @Transactional
    @Modifying
    @Query("UPDATE hotspot SET numReports = (numReports + 1) WHERE location = :location AND category = :category")
    void incrementHotspotReport(@Param("location") Long location, @Param("category") Long categoryId);

    @Transactional
    @Modifying
    @Query("UPDATE hotspot SET numReports = (numReports -1) WHERE location = :location AND category = :category")
    void decrementHotspotReport(@Param("location") Long location, @Param("category") Long categoryId);
}

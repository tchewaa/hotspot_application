package com.application.hotspotapplication.requests.hotspots.UsersHotspots;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersHotspotsDAO extends JpaRepository<UsersHotspots, UsersHotspotsId> {

    Optional<UsersHotspots> findByLocationIdAndCategoryIdAndUserId(Long locationId, Long categoryId, Long userId);
}

package com.application.hotspotapplication.requests.hotspots.UsersHotspots;

import com.application.hotspotapplication.requests.hotspots.Category.Category;
import com.application.hotspotapplication.requests.hotspots.Location.Location;
import com.application.hotspotapplication.requests.users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users_hotspots")
@Table(name = "users_hotspots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersHotspots {

    @EmbeddedId
    private UsersHotspotsId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;

    @JsonIgnore
    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Users user;

    public UsersHotspots(Location location, Category category, Users user) {
        this.location = location;
        this.category = category;
        this.user = user;
        this.id = new UsersHotspotsId(location.getId(), category.getId(), user.getId());
    }
}

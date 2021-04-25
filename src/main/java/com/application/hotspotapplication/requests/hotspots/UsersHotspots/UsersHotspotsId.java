package com.application.hotspotapplication.requests.hotspots.UsersHotspots;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersHotspotsId implements Serializable {
    @Column(name = "user_id")
    private Long userId ;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "category_id")
    private Long categoryId;


    @Override
    public boolean equals(Object obj){
        if(obj instanceof UsersHotspotsId){
            return this.getLocationId().equals(((UsersHotspotsId) obj).getLocationId()) &&
                    this.getCategoryId().equals(((UsersHotspotsId) obj).getCategoryId()) &&
                    this.userId.equals(((UsersHotspotsId) obj).getUserId());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.categoryId, this.locationId, this.userId);
    }
}

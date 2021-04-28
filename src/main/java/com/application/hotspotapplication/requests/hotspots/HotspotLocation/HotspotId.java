package com.application.hotspotapplication.requests.hotspots.HotspotLocation;

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
public class HotspotId implements Serializable {

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "category_id")
    private Long categoryId;

    @Override
    public boolean equals(Object obj){
        if(obj instanceof HotspotId){
            return this.getLocationId().equals(((HotspotId) obj).getLocationId()) &&
                    this.getCategoryId().equals(((HotspotId) obj).getCategoryId());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.categoryId, this.locationId);
    }
}

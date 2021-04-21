package com.application.hotspotapplication.requests.hotspots.Location;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
public class LocationData implements Serializable {
    private List<Location> data;

    public List<Location> getData() {
        return data;
    }

    public void setData(List<Location> data) {
        this.data = data;
    }
}

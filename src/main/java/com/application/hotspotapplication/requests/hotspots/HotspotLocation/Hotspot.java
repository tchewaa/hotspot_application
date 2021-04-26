package com.application.hotspotapplication.requests.hotspots.HotspotLocation;
import com.application.hotspotapplication.requests.hotspots.Category.Category;
import com.application.hotspotapplication.requests.hotspots.Location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "hotspot")
@Table(name = "hotspot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotspot  {

  @EmbeddedId
  private HotspotId id;

  @ManyToOne
  @MapsId("locationId")
  @JoinColumn(name = "location_id")
  private Location location;

  @ManyToOne
  @MapsId("categoryId")
  @JoinColumn(name = "category_id")
  private Category category;

  @Column(name = "num_reports")
  private Integer numReports =0;

  public Hotspot(Location location, Category category){
      this.category = category;
      this.location = location;
      this.id = new HotspotId(location.getId(), category.getId());
  }

  public void addReport(){
    numReports++;
  }

  public void deleteReport(){ numReports--; }

}
package com.application.hotspotapplication.requests.hotspots.Location;

import com.application.hotspotapplication.requests.hotspots.HotspotLocation.Hotspot;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "location")
@Table(name = "`location`")
@Data
@ToString
@NoArgsConstructor
public class Location implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "location_id")
  private Long id;
  @Column(name = "latitude")
  private Double latitude;
  @Column(name = "logitude")
  private Double longitude;
  @Column(name = "street_address")
  private String streetAddress;
  @Column(name = "region")
  private String region;
  @Column(name = "neighbourhood")
  private String neighbourhood;
  @Column(name = "postal_code")
  private Integer postalCode;
  @Column(name = "city")
  private String city;
  @Column(name = "confidence")
  private Double confidence;

  @OneToMany(
          mappedBy = "location",
          cascade = CascadeType.ALL
  )
  private List<Hotspot> hotspots = new ArrayList<>();

}
package com.application.hotspotapplication.requests.hotspots.Location;

import com.application.hotspotapplication.requests.hotspots.HotspotLocation.Hotspot;
import com.application.hotspotapplication.requests.hotspots.UsersHotspots.UsersHotspots;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
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
  @JsonIgnore
  @OneToMany(
          mappedBy = "location",
          cascade = CascadeType.ALL
  )
  private List<Hotspot> hotspots = new ArrayList<>();
  @JsonIgnore
  @OneToMany(
          mappedBy = "location",
          cascade = CascadeType.ALL
  )
  private List<UsersHotspots> usersHotspots = new ArrayList<>();

  public void setLocationData(String streetAddress, String city, String neighbourhood, Integer postalCode){
    this.streetAddress = streetAddress;
    this.city = city;
    this.neighbourhood = neighbourhood;
    this.postalCode = postalCode;
  }
}
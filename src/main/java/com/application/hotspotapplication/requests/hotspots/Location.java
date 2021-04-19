package com.application.hotspotapplication.requests.hotspots;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "location")
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
  @Column(name = "description")
  private String description;
  @Column(name = "scale_serious")
  private Integer scaleSeriousness;
  @Column(name = "category_id")
  private Integer categoryId;
  @Column(name = "date")
  private Date date;
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
}
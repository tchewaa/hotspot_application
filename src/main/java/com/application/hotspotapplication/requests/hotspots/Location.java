package com.application.hotspotapplication.requests.hotspots;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "location")
@Data
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
  private int scaleSeriousness;
  @Column(name = "category_id")
  private int categoryId;
  @Column(name = "date")
  private Date date;

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getScaleSeriousness() {
    return scaleSeriousness;
  }

  public void setScaleSeriousness(int scaleSeriousness) {
    this.scaleSeriousness = scaleSeriousness;
  }

  public int getCategory() {
    return categoryId;
  }

  public void setCategory(int categoryId) {
    this.categoryId = categoryId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
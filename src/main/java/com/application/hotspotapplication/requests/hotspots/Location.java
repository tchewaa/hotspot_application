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

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getNeighbourhood() {
    return neighbourhood;
  }

  public void setNeighbourhood(String neighbourhood) {
    this.neighbourhood = neighbourhood;
  }

  public Integer getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(int postalCode) {
    this.postalCode = postalCode;
  }

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

  public Integer getScaleSeriousness() {
    return scaleSeriousness;
  }

  public void setScaleSeriousness(int scaleSeriousness) {
    this.scaleSeriousness = scaleSeriousness;
  }

  public Integer getCategory() {
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
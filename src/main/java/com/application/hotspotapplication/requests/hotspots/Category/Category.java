package com.application.hotspotapplication.requests.hotspots.Category;

import com.application.hotspotapplication.requests.hotspots.HotspotLocation.Hotspot;
import com.application.hotspotapplication.requests.hotspots.UsersHotspots.UsersHotspots;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "category")
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long id;

  @Column(name = "category_name")
  private String name;

  @JsonIgnore
  @OneToMany(
          mappedBy = "category",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  private List<Hotspot> hotspots = new ArrayList<>();

  @JsonIgnore
  @OneToMany(
          mappedBy = "category",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  private List<UsersHotspots> usersHotspots = new ArrayList<>();

  public Category(String name){
    this.name = name;
  }


}
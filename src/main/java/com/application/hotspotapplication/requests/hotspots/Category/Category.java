package com.application.hotspotapplication.requests.hotspots.Category;

import com.application.hotspotapplication.requests.hotspots.HotspotLocation.Hotspot;
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

  @OneToMany(
          mappedBy = "category",
          cascade = CascadeType.ALL
  )
  private List<Hotspot> hotspots = new ArrayList<>();

  public Category(String name){
    this.name = name;
  }


}
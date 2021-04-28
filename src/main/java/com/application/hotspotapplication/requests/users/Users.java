package com.application.hotspotapplication.requests.users;

import com.application.hotspotapplication.requests.hotspots.UsersHotspots.UsersHotspots;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "active")
  private Boolean active;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private List<UsersHotspots> usersHotspots = new ArrayList<>();
}
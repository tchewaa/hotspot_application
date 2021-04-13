package com.application.hotspotapplication.requests.users;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users implements Serializable {

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

}
package com.application.hotspotapplication.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Override
  public String toString() {
    return "{" +
        "id=" + id +
        ", fristName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
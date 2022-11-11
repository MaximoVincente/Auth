package com.cyberZork.authenticationLab.models;

import javax.persistence.*;

@Entity
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
//  private String[] steps;

//The recipe model has a Many to one relationships with the siteUser model
  @ManyToOne
  SiteUser siteUser;
//Default constructor needed for Postgres
  protected Recipe() {
  }
//Constructor for recipe
  public Recipe(String name, String description) {
    this.name = name;
    this.description = description;
  }

  //Getters and setters for recipe model
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}

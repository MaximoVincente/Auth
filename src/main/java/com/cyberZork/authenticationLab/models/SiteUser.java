package com.cyberZork.authenticationLab.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class SiteUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
//siteUser has a one to many relationship with recipe model, it is important to remember to use the same name youre mapping by (siteUser), when creating the relation with the many to one of recipe model
  @OneToMany(mappedBy = "siteUser")
        //List where recipe elements will be inserted
  List<Recipe> recipes;

  private String userName;
  private String password;

  public SiteUser(String userName, String password){
    this.userName = userName;
    this.password = password;
  }
//Default constructor
  protected SiteUser(){
  }
//Getters and Setters
  public Long getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }
}

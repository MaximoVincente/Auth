package com.cyberZork.authenticationLab.controllers;

import com.cyberZork.authenticationLab.models.SiteUser;
import com.cyberZork.authenticationLab.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

  @Autowired
  SiteUserRepository siteUserRepository;

//Is mapped to the home which returns to the login page
  @GetMapping("/")
  public String getHome(){
    return "login.html";
  }

  //The signup page will let the user create a username, and a hashed password using Bcrypt and 10 rounds og gensalt. Then it will save the user to the database, and redirect the user to home page if signup was succesful.
  @PostMapping("/signup")
  public RedirectView signUp(String userName, String password){
    // hash pw
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
    // new User instance with hashedPW
    SiteUser newUser = new SiteUser(userName, hashedPassword);
    //save to DB
    siteUserRepository.save(newUser);
    // Redirect?
    return new RedirectView("/");
  }

  //The login route allows the user to login. If the login is correct(username, and password which is Bcrypt), then redirect the user the recipe page, and a secure Session will initiate. Else return the user the home page with the message.
  @PostMapping("/login")
  public RedirectView login(HttpServletRequest request, String userName, String password){
    // Find user by username
    SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
    // conditional
      // 1. Is user null? -> login.html
    if (siteUser != null) {
      // 2. compare DB password to given password
      if(BCrypt.checkpw(password, siteUser.getPassword())) {
        // generate a secured session
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        return new RedirectView("/recipe");
      }
      return new RedirectView("/?message=Bad Password");
    }
    return new RedirectView("/?message=No User");
  }

}

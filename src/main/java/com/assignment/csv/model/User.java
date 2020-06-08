package com.assignment.csv.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy =GenerationType.AUTO)
  @Column(name = "userId")
  private long id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Email" ,unique=true)
  private String email;
  
  
  @ManyToMany
  @JoinTable(name = "user_role_link", joinColumns = @JoinColumn(name = "userId"),
  inverseJoinColumns = @JoinColumn(name="roleId")
		  )
  List<Roles> roles; 
   


  public User() {

  }


/**
 * @return the id
 */
public long getId() {
	return id;
}


/**
 * @param id the id to set
 */
public void setId(long id) {
	this.id = id;
}


/**
 * @return the name
 */
public String getName() {
	return name;
}


/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}


/**
 * @return the email
 */
public String getEmail() {
	return email;
}


/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}


@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
}


/**
 * @return the roles
 */
public List<Roles> getRoles() {
	return roles;
}


/**
 * @param roles the roles to set
 */
public void setRoles(List<Roles> roles) {
	this.roles = roles;
}


 
public void add(Roles roles) {
	getRoles().add(roles);
}


}

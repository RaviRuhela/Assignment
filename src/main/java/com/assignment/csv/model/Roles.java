package com.assignment.csv.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {

  @Id
  @GeneratedValue(strategy =GenerationType.AUTO)
  @Column(name = "RoleId")
  private long id;

  @Column(name = "RoleName")
  private String roleName;
  
  @ManyToMany(mappedBy = "roles")
  List<User> user;
  

/**
 * @return the user
 */
public List<User> getUser() {
	return user;
}

/**
 * @param user the user to set
 */
public void setUser(List<User> user) {
	this.user = user;
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
 * @return the roleName
 */
public String getRoleName() {
	return roleName;
}

/**
 * @param roleName the roleName to set
 */
public void setRoleName(String roleName) {
	this.roleName = roleName;
}

 



  

}

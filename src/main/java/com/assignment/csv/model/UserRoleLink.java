package com.assignment.csv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role_link")
public class UserRoleLink {

  @Id
  @GeneratedValue(strategy =GenerationType.AUTO)
  @Column(name = "id")
  private long id;
  
  @Column(name = "userId")
  private long userId;
  
  @Column(name = "roleId")
  private long roleId;

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
 * @return the userId
 */
public long getUserId() {
	return userId;
}

/**
 * @param userId the userId to set
 */
public void setUserId(long userId) {
	this.userId = userId;
}

/**
 * @return the roleId
 */
public long getRoleId() {
	return roleId;
}

/**
 * @param roleId the roleId to set
 */
public void setRoleId(long roleId) {
	this.roleId = roleId;
}


  
  

}

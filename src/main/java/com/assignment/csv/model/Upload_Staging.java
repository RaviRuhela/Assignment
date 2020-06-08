package com.assignment.csv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upload_staging")
public class Upload_Staging {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "id")
	private long pid;


  @Column(name = "txnId")
  private String id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Email")
  private String email;
  
  @Column(name = "roles")
  private String role;
  
  @Column(name = "error")
  private String error;
  
  


  /**
 * @return the error
 */
public String getError() {
	return error;
}


/**
 * @param error the error to set
 */
public void setError(String error) {
	this.error = error;
}


public Upload_Staging() {

  }


/**
 * @return the id
 */
public String getId() {
	return id;
}


/**
 * @param id the id to set
 */
public void setId(String id) {
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


/**
 * @return the role
 */
public String getRole() {
	return role;
}


/**
 * @param role the role to set
 */
public void setRole(String role) {
	this.role = role;
}


@Override
public String toString() {
	return "Upload_Staging [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + ", error=" + error
			+ "]";
}


  

}

package com.assignment.csv.message;

public class ResponseMessage {
private String message;	
  private String no_of_rows_parsed;
  private String no_of_rows_failed;
  private String error_file_url;
  
 
  
/**
 * @return the message
 */
public String getMessage() {
	return message;
}
/**
 * @param message the message to set
 */
public void setMessage(String message) {
	this.message = message;
}
/**
 * @return the no_of_rows_parsed
 */
public String getNo_of_rows_parsed() {
	return no_of_rows_parsed;
}
/**
 * @param no_of_rows_parsed the no_of_rows_parsed to set
 */
public void setNo_of_rows_parsed(String no_of_rows_parsed) {
	this.no_of_rows_parsed = no_of_rows_parsed;
}
/**
 * @return the no_of_rows_failed
 */
public String getNo_of_rows_failed() {
	return no_of_rows_failed;
}
/**
 * @param no_of_rows_failed the no_of_rows_failed to set
 */
public void setNo_of_rows_failed(String no_of_rows_failed) {
	this.no_of_rows_failed = no_of_rows_failed;
}
/**
 * @return the error_file_url
 */
public String getError_file_url() {
	return error_file_url;
}
/**
 * @param error_file_url the error_file_url to set
 */
public void setError_file_url(String error_file_url) {
	this.error_file_url = error_file_url;
}
  

  
}

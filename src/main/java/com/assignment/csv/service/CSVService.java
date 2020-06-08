package com.assignment.csv.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.csv.helper.CSVHelper;
import com.assignment.csv.message.ResponseMessage;
import com.assignment.csv.model.Roles;
import com.assignment.csv.model.Tutorial;
import com.assignment.csv.model.Upload_Staging;
import com.assignment.csv.model.User;
import com.assignment.csv.repository.ErrorRepository;
import com.assignment.csv.repository.RoleRepository;
import com.assignment.csv.repository.TutorialRepository;
import com.assignment.csv.repository.UserRepository;
import com.assignment.dto.CSVData;

@Service
public class CSVService {
  @Autowired
  TutorialRepository repository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  ErrorRepository errorRepository;
  

  public ResponseMessage save(MultipartFile file) throws Exception {
	  //return of method
	  ResponseMessage resp=new ResponseMessage();
	  //date format
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	  Date date = new Date();  
	  String strDate = dateFormat.format(date);
	  //key for staging table
	  String key="FileUpload_UserCreate_"+strDate;
    try {
    
    //Reading csv data 	
      List<CSVData> fileData = CSVHelper.csvToRegister(file.getInputStream());
      //checking null
      if(null!=fileData) {
    	  //list of users
    	  List<User> user=new ArrayList<User>();
    	  //list of roles
    	  List<Roles> roles=new ArrayList<Roles>();
    	  //list of staging table data
    	  List<Upload_Staging> errors=new ArrayList<>();
    	  //loop
    	  for(CSVData item:fileData) {
    		  //flag for error check
    		  boolean flag=true;
    		  User usr=new User();
    		  //new list for # seprated roles
    		  List<Roles> rol=new ArrayList<Roles>();
    		  //validating Email
    		  if(null!=item.getEmail() &&  !item.getEmail().isEmpty()) {
    			  //checking duplicate emails
    			 User duplicate= userRepository.findByEmailAddress(item.getEmail());
    			  if(null!=duplicate) {
    				  item.setError("Email Id already exist");
        			  flag=false;
    			  }
    			  else
    				  usr.setEmail(item.getEmail());
    		  }else {
    			  item.setError(item.getError()+"#"+"Invalid/Blank Email");
    			  flag=false;
    		  }
    		  //validate Name
    		  if(null!=item.getName() &&  !item.getName().isEmpty()) {
    			  usr.setName(item.getName());
    		  }else {
    			  item.setError(item.getError()+"#"+"Invalid/Blank Name");
    			  flag=false;
    		  }
    		  //Validate Roles
    		  if(null!=item.getRoles() &&  !item.getRoles().isEmpty()) {
    			  String[] array=item.getRoles().split("#");
    			  for(String rl:array) {
    				  Roles role=new Roles();
    				  role.setRoleName(rl);
    				  rol.add(role);
    			  }
    		  }else {
    			  item.setError(item.getError()+"#"+"Invalid/Blank Role");
    			  flag=false;
    		  }
    		  //check flag and add object into list
    		  if(flag) {
    			  user.add(usr);
    			  roles.addAll(rol);
    		  }
    		  //else add into error list
    		  else {
    			  Upload_Staging uploadError=new Upload_Staging();
    			  uploadError.setEmail(item.getEmail());
    			  uploadError.setName(item.getName());
    			  uploadError.setRole(item.getRoles());
    			  uploadError.setError(item.getError());
    			  uploadError.setId(key);
    			  errors.add(uploadError);
    		  }
    		  
    	  }
    	  //save users
    	  userRepository.saveAll(user);
    	  //save roles
    	  roleRepository.saveAll(roles);
    	  //save errors
    	  errorRepository.saveAll(errors);
			/*
			 * for(int i=0;i<user.size();i++) { UserRoleLink combo=new UserRoleLink();
			 * combo.setRoleId(roles.get(i).getId()); combo.setUserId(user.get(0).getId());
			 * roleLink.add(combo); } comboRepository.saveAll(roleLink);
			 */
    	  //setting response
    	  resp.setNo_of_rows_failed(String.valueOf(fileData.size()-user.size()));
    	  resp.setNo_of_rows_parsed(String.valueOf(fileData.size()));
    	  if(errors==null || errors.size()==0)
    		  resp.setError_file_url(key);
    	  
      }else {
    	  resp.setNo_of_rows_parsed("No rows has been parsed");
      }
      //repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
    return resp;
  }

  public ByteArrayInputStream load(String url) {
	 ByteArrayInputStream in=null;
	try {  
    List<Upload_Staging> list = errorRepository.findByStaging(url);
    //List<Upload_Staging> list=toList(data);
    in = CSVHelper.ToCSV(list);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return in;
  }
  public static <T> List<T> toList(Optional<T> opt) {
	    return opt.isPresent()
	            ? Collections.singletonList(opt.get())
	            : Collections.emptyList();
	}

  public List<Tutorial> getAllTutorials() {
    return repository.findAll();
  }
}

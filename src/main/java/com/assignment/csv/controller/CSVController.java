package com.assignment.csv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.csv.helper.CSVHelper;
import com.assignment.csv.message.ResponseMessage;
import com.assignment.csv.service.CSVService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api/csv")
public class CSVController {

	@Autowired
	CSVService fileService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		String message = "";
		ResponseMessage resp = new ResponseMessage();
		ObjectMapper mapper = new ObjectMapper();

		// checking correct file format
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				// method to save file data
				resp = fileService.save(file);
				// File is saved successfully
				message = "file Uploaded successfully: " + file.getOriginalFilename();
				resp.setMessage(message);
				return new ResponseEntity<>(mapper.writeValueAsString(resp), HttpStatus.OK);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				resp.setMessage(message);
				return new ResponseEntity<>(mapper.writeValueAsString(resp), HttpStatus.OK);
			}
		}
		message = "Please upload a csv file!";
		resp.setMessage(message);
		return new ResponseEntity<>(mapper.writeValueAsString(resp), HttpStatus.OK);
	}

	@PostMapping("/download")
	public ResponseEntity<Resource> getFile(@RequestParam("url") String url) {
		String filename = "errors.csv";
		InputStreamResource file = new InputStreamResource(fileService.load(url));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

}

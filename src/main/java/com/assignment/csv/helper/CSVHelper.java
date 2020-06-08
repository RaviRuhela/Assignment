package com.assignment.csv.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.csv.model.Upload_Staging;
import com.assignment.dto.CSVData;

public class CSVHelper {
	public static String TYPE = "text/csv";

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

//method to get csv Data in List form
	public static List<CSVData> csvToRegister(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<CSVData> list = new ArrayList<CSVData>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				CSVData dto = new CSVData();
				dto.setEmail(csvRecord.get("Email"));
				dto.setName(csvRecord.get("Name"));
				dto.setRoles(csvRecord.get("Roles"));
				list.add(dto);
			}

			return list;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

//method to convert list to CSV
	public static ByteArrayInputStream ToCSV(List<Upload_Staging> data) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		// try with resource
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			// Header Column Names
			List<String> header = Arrays.asList(String.valueOf("Name"), "Email", "Role", "Error");
			// print header Columns
			csvPrinter.printRecord(header);
			// loop over staging Data
			for (Upload_Staging item : data) {
				List<String> output = Arrays.asList(String.valueOf(item.getName()), item.getEmail(), item.getRole(),
						String.valueOf(item.getError()));
				csvPrinter.printRecord(output);
			}
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

}

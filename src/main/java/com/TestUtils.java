package com;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;

public class TestUtils {
	public static final String HTML_NEW_LINE = "<br/>";
	public static final String REGEX_IP = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	
	public static String constructInvalidIP(String path){

		long startTime = System.currentTimeMillis();
		BufferedReader reader;
		StringBuilder sbInvalid = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(
					path));
			String line = reader.readLine();
			while (line != null) {
				if (!validateIP(line)){
					sbInvalid.append(line);
					sbInvalid.append(HTML_NEW_LINE);
				}
				

				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			//logger.error(e);
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		sbInvalid.append(displayTimeConsumed(startTime,endTime));
		
		return sbInvalid.toString();
	}
	
	public static String displayTimeConsumed (long start,long end){
		StringBuilder sb = new StringBuilder();
		sb.append(HTML_NEW_LINE);
		sb.append(HTML_NEW_LINE);
		sb.append(HTML_NEW_LINE);
		sb.append((end-start)/1000==0?(end-start) + " miliseconds":(end-start)/1000 + " seconds");
		
		return sb.toString();
	}
	
	public static boolean validateIP(String ip){
		if(StringUtils.isEmpty(ip)|| ip.matches(REGEX_IP)){
			return true;
		}
		return false;
	}
}

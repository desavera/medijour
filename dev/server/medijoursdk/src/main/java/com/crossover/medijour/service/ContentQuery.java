package com.crossover.medijour.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ContentQuery {

	
	// the default date format
	public final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	final static Logger logger = Logger.getLogger(ContentQuery.class); 

		
	
	private String origin;	
	private String destiny;
	private String departureTime;	
	private String returnTime;
	private Integer seats;
	
	
	
	
	public ContentQuery() {
		
	}
	
	public ContentQuery(String origin, String destiny, String departuring, String returning,
			int npassengers) {
		this.origin = origin.toString();
		this.destiny = destiny.toString();
		this.departureTime = departuring.toString();
		this.returnTime = returning.toString();
		this.seats = npassengers;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returning) {
		this.returnTime = returning;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departuring) {
		this.departureTime = departuring;
	}
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String scheduleOrigin) {
		this.origin = scheduleOrigin;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String scheduleDestiny) {
		this.destiny = scheduleDestiny;
	}
	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer npassengers) {
		this.seats = npassengers;
	}
	
	//
	// Methods
	//
	
	/*
	 * Utility method assuming a full date string format
	 */
	public static Date createQueryDateFromString(String dateString) {
		
		try {
			return SDF.parse(dateString.substring(0, 10) + " 00:00:00");

		} catch (ParseException e) {

			logger.error("Error parsing date string query date strings..." + dateString + e);
		}
		
		return null;

	}

	// for debugging purposes
	public String toString() {
	
		StringBuffer str = new StringBuffer();
		str.append("Origin : " + origin + '\n');
		str.append("Destiny : " + destiny + '\n');
		str.append("Departuring : " + departureTime + '\n');
		str.append("Returning : " + returnTime + '\n');
		str.append("N of Passengers : " + seats + '\n');		
		
		return str.toString();
	}


}

package com.crossover.medijour.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "journals")
public class Journals implements Serializable {

	private final static long serialVersionUID = 1L;

	final Logger logger = Logger.getLogger(Journals.class);

	//
	// Data members
	//
	private Integer id;
	private String dataPath;
	private boolean available;
	private String header;
	private Integer pubId;

	//
	// Accessors/Mutators
	//
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer sch_id) {
		this.id = sch_id;
	}
	
	@Column(name = "data_path")
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	

	@Column(name = "available")
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	@Column(name = "header")
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	@Column(name = "pubid")	
	public Integer getPubId() {
		return pubId;
	}

	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Journals))
			return false;
		Journals other = (Journals) obj;
		if (id != other.id)
			return false;
		return true;
	}


	public String toString() {

		StringBuffer str = new StringBuffer();
		str.append("Id : " + id + '\n');
		str.append("dataPath : " + dataPath + '\n');
		str.append("Available : " + available + '\n');
		str.append("Header : " + header + '\n');
		str.append("PubId : " + pubId + '\n');

		return str.toString();
	}

	public void update(Journals journal) {

		if (this.equals(journal)) {
			
			this.dataPath = journal.getDataPath().toString();
			this.available = journal.isAvailable();
			this.header = journal.getHeader().toString();
			this.pubId = journal.getPubId();
			
		} else throw new IllegalArgumentException();
			
		
	}

}

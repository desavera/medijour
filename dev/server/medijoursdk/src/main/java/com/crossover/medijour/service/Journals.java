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
	private String path;
	private Boolean available;
	private String header;
	private Integer pubid;

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
	
	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

	@Column(name = "available")
	public Boolean isAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
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
		return pubid;
	}

	public void setPubId(Integer pubId) {
		this.pubid = pubId;
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
		str.append("dataPath : " + path + '\n');
		str.append("Available : " + available + '\n');
		str.append("Header : " + header + '\n');
		str.append("PubId : " + pubid + '\n');

		return str.toString();
	}

	public void update(Journals journal) {

		if (this.equals(journal)) {
			
			this.path = journal.getPath().toString();
			this.available = journal.isAvailable();
			this.header = journal.getHeader().toString();
			this.pubid = journal.getPubId();
			
		} else throw new IllegalArgumentException();
			
		
	}

}

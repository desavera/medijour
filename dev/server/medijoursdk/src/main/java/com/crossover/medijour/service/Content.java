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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "content")
public class Content implements Serializable {

	private final static long serialVersionUID = 1L;

	final Logger logger = Logger.getLogger(Content.class);

	//
	// Data members
	//
	private Integer id;

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
/*
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "schedule_departure_time")
	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;		
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "schedule_arriving_time")
	public Date getArrivingTime() {
		return arrivingTime;
	}

	public void setArrivingTime(Date arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	@Column(name = "schedule_costs")
	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}	
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "schedule_origin", insertable = false, updatable = false)
	public Spot getOrigin() {
		return origin;
	}

	public void setOrigin(Spot origin) {
		this.origin = origin;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "schedule_destiny", insertable = false, updatable = false)	
	public Spot getDestiny() {
		return destiny;
	}

	public void setDestiny(Spot destiny) {
		this.destiny = destiny;
	}

	@Column(name = "schedule_available_seats")
	public Integer getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(Integer nseats) {
		this.numOfSeats = nseats;
	}


	//
	// Methods
	//	
	public void update(Content schedule) {

		if (schedule.getId() == this.getId()) {

			this.setDepartureTime(schedule.getDepartureTime());
			this.setDestiny(schedule.getDestiny());
			this.setNumOfSeats(schedule.getNumOfSeats());
			this.setOrigin(schedule.getOrigin());

		} else
			throw new IllegalArgumentException(
					"invalid update for schedule id : " + schedule.getId() + " to entity id : " + this.getId());

	}
*/
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
		if (!(obj instanceof Content))
			return false;
		Content other = (Content) obj;
		if (id != other.id)
			return false;
		return true;
	}


	public String toString() {

		StringBuffer str = new StringBuffer();
		str.append("Id : " + id + '\n');

		return str.toString();
	}

	public void update(Content content) {
		// TODO Auto-generated method stub
		
	}

}

package com.crossover.medijour.service;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the journals database table.
 * 
 */
@Entity
@Table(name="journals")
@NamedQuery(name="Journals.findAll", query="SELECT j FROM Journals j")
public class Journals implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte available;

	private String header;

	private String path;

	//bi-directional many-to-one association to Publisher
	@ManyToOne
	@JoinColumn(name="pubid")
	private Publisher publisher;

	//bi-directional many-to-one association to Subscription
	@OneToMany(mappedBy="journal")
	private List<Subscription> subscriptions;

	public Journals() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getAvailable() {
		return this.available;
	}

	public void setAvailable(byte available) {
		this.available = available;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Publisher getPublisher() {
		return this.publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Subscription> getSubscriptions() {
		return this.subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Subscription addSubscription(Subscription subscription) {
		getSubscriptions().add(subscription);
		subscription.setJournal(this);

		return subscription;
	}

	public Subscription removeSubscription(Subscription subscription) {
		getSubscriptions().remove(subscription);
		subscription.setJournal(null);

		return subscription;
	}
	
	public boolean isAvailable() {
		
		return (this.getAvailable() != 0);		
		
	}

	public void setAvailable(boolean b) {
		
		if (b) this.setAvailable((byte)1);
		
		else this.setAvailable((byte)0);
		
	}

}
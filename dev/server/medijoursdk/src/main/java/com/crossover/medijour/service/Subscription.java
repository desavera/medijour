package com.crossover.medijour.service;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the subscription database table.
 * 
 */
@Entity
@Table(name="subscription")
@NamedQuery(name="Subscription.findAll", query="SELECT s FROM Subscription s")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	//bi-directional many-to-one association to Journal
	@ManyToOne
	@JoinColumn(name="jrnid")
	private Journals journal;

	//bi-directional many-to-one association to Subscriber
	@ManyToOne
	@JoinColumn(name="subid")
	private Subscriber subscriber;

	public Subscription() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Journals getJournal() {
		return this.journal;
	}

	public void setJournal(Journals journal) {
		this.journal = journal;
	}

	public Subscriber getSubscriber() {
		return this.subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

}
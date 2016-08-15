package com.crossover.medijour.service;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the publisher database table.
 * 
 */
@Entity
@Table(name="publisher")
@NamedQuery(name="Publisher.findAll", query="SELECT p FROM Publisher p")
public class Publisher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String email;

	private String name;

	private String provunq;

	//bi-directional many-to-one association to Journal
	@OneToMany(mappedBy="publisher")
	@JsonManagedReference
	private List<Journals> journals;

	public Publisher() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvunq() {
		return this.provunq;
	}

	public void setProvunq(String provunq) {
		this.provunq = provunq;
	}

	public List<Journals> getJournals() {
		return this.journals;
	}

	public void setJournals(List<Journals> journals) {
		this.journals = journals;
	}

	public Journals addJournal(Journals journal) {
		getJournals().add(journal);
		journal.setPublisher(this);

		return journal;
	}

	public Journals removeJournal(Journals journal) {
		getJournals().remove(journal);
		journal.setPublisher(null);

		return journal;
	}

}
package com.crossover.mjreader.model;

import java.io.Serializable;


public class Journals implements Serializable {

	private final static long serialVersionUID = 1L;

	//
	// Data members
	//
	private Integer id;
	private String name;

	public Journals(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	//
	// Accessors/Mutators
	//
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}


	//
	// Methods
	//	
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
		str.append("Name : " + name + '\n');

		return str.toString();
	}

	public void update(Journals journals) {
		
		if (journals.equals(this))
			this.setName(journals.getName());
		
	}

}

package com.acompany.photoi.model;

import java.io.Serializable;



public class User implements Serializable {

	private final static long serialVersionUID = 1L;

	public static final Integer NOTLOGGED = null;
	public static final Integer LOGGED = null;

	//
	// Data members
	//
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;	
	private String email;
	private String password;
	private String phone;
	private Integer userStatus;
	
	
	public User(Integer id, String name,String firstName,String lastName,String email,String password,String phone,Integer userStatus) {
		
		this.id = id;
		this.username = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userStatus = userStatus;
	}

	public User() {
		this(-1,"Error...","Error...","Error...","Error...","Error...","Error...",-1);
	}

	// for login/logout porpouses
	public User(String username) {
		this();
		setUsername(username);
	}

	//
	// Accessors/Mutators
	//
	public Integer getId() {
		return id;
	}

	public void setId(Integer res_id) {
		this.id = res_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	} 	

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
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
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		
		StringBuffer str = new StringBuffer();
		str.append("Id : " + id + '\n');
		str.append("Username : " + username + '\n');
		
		return str.toString();
	}

	public void update(User user) {

		if (user.equals(this)) {

			this.id = id;
			this.username = user.getUsername();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.email = user.getEmail();
			this.password = user.getPassword();
			this.phone = user.getPhone();
			this.userStatus = user.getUserStatus();							
		}
	}


}

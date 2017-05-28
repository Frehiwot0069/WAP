package edu.mum.wap.model;

import java.util.Date;

public class User {
	private int userId;
	private String fullName;
	private String gender;
	private String state;
	private String city;
	private String street;
	private String zipCode;

	private Date birthDate;
	private String email;
	private String password;

	private Date dateCreated;

	private Date dateUpdated;
	
	
	public User() {}
	
	public User(int userId, String fullName, String gender, String state, String city, String street, String zipCode) {
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.city = city;
		this.street = street;
		this.state = state;
		this.zipCode = zipCode;		
	}

	public User(int userId, String fullName, String gender, String state, String city, String street,
			String zipCode, Date birthDate, String email, String password, Date dateCreated,
			Date dateUpdated) {
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
		this.birthDate = birthDate;
		this.email = email;
		
		this.password = password;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFullAddress() {
		return street + ", " + city + ", " + state + ", " + "United States " + zipCode;
	}

	@Override
	public String toString() {
		/*return "{\"userId\" : " + userId + ", \"fullName\" : \"" + fullName + "\", \"gender\" : \"" + gender + "\", \"state\" : \"" + state
				+ "\", \"city\" : \"" + city + "\", \"street\" : \"" + street + "\", \"zipCode\" : \"" + zipCode + "\", \"birthDate\" : \"" + birthDate
				+ "\", \"email\" : \"" + email + "\", \"password\" : \"" + password + "\"}";*/
		return "{\"userId\" : " + userId + ", \"fullName\" : \"" + fullName + "\", \"gender\" : \"" + gender + "\", \"street\" : \"" + street + "\", \"city\" : \"" + city + "\", \"state\" : \"" + state + "\", \"zip\" : \"" + zipCode + "\"}";
	}
	
}

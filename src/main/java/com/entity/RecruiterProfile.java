package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "recruiter_profile")
public class RecruiterProfile {
	
	@Id
	private int userAccount;
	
	@OneToOne
	@JoinColumn(name="user_account_id")  
	@MapsId
	private Users userId;
	
	private String city;
	
	private String company;
	
	private String country;
	
	private String firstName;
	
	private String lastName;
	
	private String state;
	
	@Column(nullable = true, length = 64)
	private String profilePhoto;

	public RecruiterProfile() {
		
	}
	
	

	public RecruiterProfile(Users userId) {
		
		this.userId = userId;
	}



	public RecruiterProfile(int userAccount, Users userId, String city, String company, String country,
			String firstName, String lastName, String state, String profilePhoto) {
		
		this.userAccount = userAccount;
		this.userId = userId;
		this.city = city;
		this.company = company;
		this.country = country;
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = state;
		this.profilePhoto = profilePhoto;
	}

	public int getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(int userAccount) {
		this.userAccount = userAccount;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
	@Transient
	public String getPhotosImagePath()
	{
		if(profilePhoto == null) 
			return null;
		
		return "/photos/recruiter/" + userAccount + "/" + profilePhoto;
	}

	@Override
	public String toString() {
		return "RecruiterProfile [userAccount=" + userAccount + ", userId=" + userId + ", city=" + city + ", company="
				+ company + ", country=" + country + ", firstName=" + firstName + ", lastName=" + lastName + ", state="
				+ state + ", profilePhoto=" + profilePhoto + "]";
	}
	
	
	
	

}

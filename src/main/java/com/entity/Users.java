package com.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	private String password;
	
	private boolean isActive;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date registrationDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userTypeId" , referencedColumnName = "userTypeId")
	private UsersType usersTypeId;

	
	public Users() {
		
	}

	public Users(int userId, String email, String password, boolean isActive, Date registrationDate,
			UsersType usersTypeId) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.registrationDate = registrationDate;
		this.usersTypeId = usersTypeId;
	}

	public int getUsersId() {
		return userId;
	}

	public void setUsersId(int usersId) {
		this.userId = usersId;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public UsersType getUsersTypeId() {
		return usersTypeId;
	}

	public void setUsersTypeId(UsersType usersTypeId) {
		this.usersTypeId = usersTypeId;
	}

	@Override
	public String toString() {
		return "Users [usersId=" + userId + ", email=" + email + ", password=" + password + ", isActive=" + isActive
				+ ", registrationDate=" + registrationDate + ", usersTypeId=" + usersTypeId + "]";
	}
	
	
	
	

}

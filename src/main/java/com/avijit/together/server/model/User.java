/*****************************************************************************
 * FILE NAME   : User.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.avijit.together.server.util.javatime.LocalDateDeserializer;
import com.avijit.together.server.util.javatime.LocalDateSerializer;
import com.avijit.together.server.util.javatime.LocalDateTimeDeserializer;
import com.avijit.together.server.util.javatime.LocalDateTimeSerializer;
import com.avijit.together.server.util.validator.Phone;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author avijit
 *
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique ID for User
	 */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	/**
	 * email of the user
	 */
	@Column(name = "EMAIL")
	@NotBlank(message = "error.email.notblank")
	@Email(message = "error.email.notemail")
	private String email;

	/**
	 * phone number of the user
	 */
	@Column(name = "PHONE")
	@NotBlank(message = "error.phone.notblank")
	@Phone(message = "error.phone.notphone")
	private String phone;

	/**
	 * First Name of the user
	 */
	@Column(name = "FIRST_NAME")
	@NotBlank(message = "error.firstname.notblank")
	@Size(min = 2, max = 30, message = "error.firstname.size.notbetween2and30")
	private String firstName;

	/**
	 * Middle name of the user
	 */
	@Column(name = "MIDDLE_NAME")
	private String middleName;

	/**
	 * Last Name of the user
	 */
	@Column(name = "LAST_NAME")
	@NotBlank(message = "error.lastname.notblank")
	@Size(min = 2, max = 30, message = "error.lastname.size.notbetween2and30")
	private String lastName;

	/**
	 * Gender of the user
	 */
	@Column(name = "GENDER", columnDefinition = "enum('MALE','FEMALE')", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/**
	 * Birthday of the user
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "BIRTHDAY")
	private LocalDate birthday;

	/**
	 * Number of device user is active
	 */
	@Column(name = "IS_ACTIVE")
	@JsonIgnore
	private byte isActive;

	/**
	 * How many other user is reported about this user
	 */
	@Column(name = "IS_REPORTED")
	@JsonIgnore
	private byte isReported;

	/**
	 * How many other user blocked this user
	 */
	@Column(name = "IS_BLOCKED")
	@JsonIgnore
	private byte isBlocked;

	/**
	 * Creation DateTime of the user
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	/**
	 * DateTime of the last update
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 *            Unique ID for User
	 * @param email
	 *            email of the user
	 * @param phone
	 *            phone number of the user
	 * @param firstName
	 *            First Name of the user
	 * @param middleName
	 *            Middle name of the user
	 * @param lastName
	 *            Last Name of the user
	 * @param gender
	 *            Gender of the user
	 * @param birthday
	 *            Birthday of the user
	 * @param isActive
	 *            Number of device user is active
	 * @param isReported
	 *            How many other user is reported about this user
	 * @param isBlocked
	 *            How many other user blocked this user
	 * @param createdAt
	 *            Creation DateTime of the user
	 * @param updatedAt
	 *            DateTime of the last update
	 */
	public User(UUID id, String email, String phone, String firstName, String middleName, String lastName,
			Gender gender, LocalDate birthday, byte isActive, byte isReported, byte isBlocked, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.isActive = isActive;
		this.isReported = isReported;
		this.isBlocked = isBlocked;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the isActive
	 */
	public byte getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isReported
	 */
	public byte getIsReported() {
		return isReported;
	}

	/**
	 * @param isReported
	 *            the isReported to set
	 */
	public void setIsReported(byte isReported) {
		this.isReported = isReported;
	}

	/**
	 * @return the isBlocked
	 */
	public byte getIsBlocked() {
		return isBlocked;
	}

	/**
	 * @param isBlocked
	 *            the isBlocked to set
	 */
	public void setIsBlocked(byte isBlocked) {
		this.isBlocked = isBlocked;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [" + (id != null ? "id=" + id + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ (phone != null ? "phone=" + phone + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (middleName != null ? "middleName=" + middleName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (gender != null ? "gender=" + gender + ", " : "")
				+ (birthday != null ? "birthday=" + birthday + ", " : "") + "isActive=" + isActive + ", isReported="
				+ isReported + ", isBlocked=" + isBlocked + ", "
				+ (createdAt != null ? "createdAt=" + createdAt + ", " : "")
				+ (updatedAt != null ? "updatedAt=" + updatedAt : "") + "]";
	}

}

/*****************************************************************************
 * FILE NAME   : User.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.model;

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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.avijit.together.server.util.javatime.LocalDateDeserializer;
import com.avijit.together.server.util.javatime.LocalDateSerializer;
import com.avijit.together.server.util.javatime.LocalDateTimeDeserializer;
import com.avijit.together.server.util.javatime.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author avijit
 *
 */
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "GENDER", columnDefinition = "enum('MALE','FEMALE','OTHERS')", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "BIRTHDAY")
	private LocalDate birthday;

	@Column(name = "IS_ACTIVE")
	private byte isActive;

	@Column(name = "IS_REPORTED")
	private byte isReported;

	@Column(name = "IS_BLOCKED")
	private byte isBlocked;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

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
	 * @param email
	 * @param phone
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param gender
	 * @param birthday
	 * @param isActive
	 * @param isReported
	 * @param isBlocked
	 * @param createdAt
	 * @param updatedAt
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

}

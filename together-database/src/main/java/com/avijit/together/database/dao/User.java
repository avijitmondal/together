/*****************************************************************************
 * FILE NAME   : User.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.dao;

import com.avijit.together.core.bean.Gender;
import com.avijit.together.core.util.javatime.LocalDateDeserializer;
import com.avijit.together.core.util.javatime.LocalDateSerializer;
import com.avijit.together.core.util.javatime.LocalDateTimeDeserializer;
import com.avijit.together.core.util.javatime.LocalDateTimeSerializer;
import com.avijit.together.core.util.validator.Phone;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author avijit
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
}

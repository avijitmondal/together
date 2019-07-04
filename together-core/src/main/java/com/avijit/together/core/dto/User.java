/*****************************************************************************
 * FILE NAME   : User.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.dto;

import com.avijit.together.core.bean.Gender;
import com.avijit.together.core.util.javatime.LocalDateDeserializer;
import com.avijit.together.core.util.javatime.LocalDateSerializer;
import com.avijit.together.core.util.javatime.LocalDateTimeDeserializer;
import com.avijit.together.core.util.javatime.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique ID for User
	 */
	private UUID id;

	/**
	 * email of the user
	 */
	private String email;

	/**
	 * phone number of the user
	 */
	private String phone;

	/**
	 * First Name of the user
	 */
	private String firstName;

	/**
	 * Middle name of the user
	 */
	private String middleName;

	/**
	 * Last Name of the user
	 */
	private String lastName;

	/**
	 * Gender of the user
	 */
	private Gender gender;

	/**
	 * Birthday of the user
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	/**
	 * Number of device user is active
	 */
	@JsonIgnore
	private byte isActive;

	/**
	 * How many other user is reported about this user
	 */
	@JsonIgnore
	private byte isReported;

	/**
	 * How many other user blocked this user
	 */
	@JsonIgnore
	private byte isBlocked;

	/**
	 * Creation DateTime of the user
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	/**
	 * DateTime of the last update
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updatedAt;
}

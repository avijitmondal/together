/*****************************************************************************
 * FILE NAME   : Credential.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.database.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author avijit
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "AUTHENTICATION")
public class Credential {

	/**
	 * Unique ID for Credential
	 */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	/**
	 * Credential username
	 */
	@Column(name = "USERNAME")
	@NotBlank(message = "error.username.notblank")
	private String username;

	/**
	 * Credential email
	 */
	@Column(name = "EMAIL")
	private String email;

	/**
	 * Credential password
	 */
	@Column(name = "PASSWORD")
	@NotBlank(message = "error.password.notblank")
	private String password;

	/**
	 * Credential enabled or not
	 */
	@Column(name = "ENABLED", columnDefinition = "CHAR", length = 1)
	private boolean enabled;

	/**
	 * DateTime when password last reset
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "LAST_PASSWORD_RESET")
	private Date lastPasswordResetDate;

	/**
	 * Authority types for that credential
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AUTHENTICATION_AUTHORITY", joinColumns = {
			@JoinColumn(name = "AUTHENTICATION_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID") })
	private List<Authority> authorities;
}

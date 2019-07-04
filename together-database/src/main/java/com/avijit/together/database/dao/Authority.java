/*****************************************************************************
 * FILE NAME   : Authority.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Table(name = "AUTHORITY")
public class Authority {

	/**
	 * Unique id for an Authority
	 */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	/**
	 * Role for an authority
	 */
	@Column(name = "ROLE", columnDefinition = "enum('ROLE_USER','ROLE_ADMIN')", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotBlank(message = "error.authorityrole.notblank")
	private AuthorityRole authorityRole;

	/**
	 * Contains list of all credentials for that specific authority type
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<Credential> credentials;
}

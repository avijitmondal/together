/*****************************************************************************
 * FILE NAME   : Authority.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author avijit
 *
 */
@Entity
@Table(name = "AUTHORITY")
public class Authority {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	@Column(name = "ROLE", columnDefinition = "enum('ROLE_USER','ROLE_ADMIN')", nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthorityRole authorityRole;

	@JsonIgnore
	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<Credential> credentials;

	/**
	 * 
	 */
	public Authority() {
		super();
	}

	/**
	 * @param id
	 * @param authorityRole
	 * @param credentials
	 */
	public Authority(UUID id, AuthorityRole authorityRole, List<Credential> credentials) {
		super();
		this.id = id;
		this.authorityRole = authorityRole;
		this.credentials = credentials;
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
	 * @return the authorityRole
	 */
	public AuthorityRole getAuthorityRole() {
		return authorityRole;
	}

	/**
	 * @param authorityRole
	 *            the authorityRole to set
	 */
	public void setAuthorityRole(AuthorityRole authorityRole) {
		this.authorityRole = authorityRole;
	}

	/**
	 * @return the credentials
	 */
	public List<Credential> getCredentials() {
		return credentials;
	}

	/**
	 * @param credentials
	 *            the credentials to set
	 */
	public void setCredentials(List<Credential> credentials) {
		this.credentials = credentials;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Authority [" + (id != null ? "id=" + id + ", " : "")
				+ (authorityRole != null ? "authorityRole=" + authorityRole : "") + "]";
	}
}

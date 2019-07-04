/*****************************************************************************
 * FILE NAME   : ErrorBean.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.bean;

/**
 * @author avijit
 *
 */
public class ErrorBean {
	/**
	 * 
	 */
	String errorCode;
	/**
	 * 
	 */
	String errorDescription;

	/**
	 * 
	 */
	public ErrorBean() {
		super();
	}

	/**
	 * @param errorCode
	 * @param errorDescription
	 */
	public ErrorBean(String errorCode, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription
	 *            the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

}

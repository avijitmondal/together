package com.avijit.together.server.util;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author avijit
 *
 */
@Component
public class TimeProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return Current date
	 */
	public Date now() {
		return new Date();
	}
}

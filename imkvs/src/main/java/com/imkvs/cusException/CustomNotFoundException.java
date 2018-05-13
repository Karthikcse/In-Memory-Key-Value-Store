/**
 * 
 */
package com.imkvs.cusException;

/**
 * @author Karthikeyan
 *
 */
public class CustomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomNotFoundException() {
		super();
	}

	public CustomNotFoundException(String message) {
		super(message);
	}
}
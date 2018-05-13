/**
 * 
 */
package com.imkvs.cusException;

/**
 * @author Karthikeyan
 *
 */
public class CustomNullPointException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomNullPointException() {
		super();
	}

	public CustomNullPointException(String message) {
		super(message);
	}
}

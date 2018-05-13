/**
 * 
 */
package com.imkvs.college;

import java.util.Set;

/**
 * @author Karthikeyan
 *
 */
public class Section {
	
	/*
	 * This will check the first character of input value and send request to the particular client 
	 * 
	 */
	public Set<Student> sendMessage(final String pKey,final Student student) {

		switch (pKey.toLowerCase().charAt(0)) {
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
			return new CollegeClient().sendMessage(student,9000);
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
			return new CollegeClient().sendMessage(student,9001);

		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			return new CollegeClient().sendMessage(student,9002);
		}
		return null;
	}
	
	/*
	 * Based on the request port it will retrieve values form in-memory cache
	 */
	public Set<Student> getAll(int port, Student student) {
		return new CollegeClient().sendMessage(student,port);
	}
}
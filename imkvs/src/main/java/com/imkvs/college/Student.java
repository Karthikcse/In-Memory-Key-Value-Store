/**
 * 
 */
package com.imkvs.college;

import java.io.Serializable;

/**
 * @author Karthikeyan
 *
 */
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;

	private String userName; // Unique Value
	private String firstName;
	private String lastName;
	private int age;
	private int mark;
	private String restMethod;

	public Student() {
		super();
	}

	public Student(String userName, String firstName, String lastName, int age, int mark, String restMethod) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.mark = mark;
		this.restMethod = restMethod;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getRestMethod() {
		return restMethod;
	}
	public void setRestMethod(String restMethod) {
		this.restMethod = restMethod;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * Overriding hashCode and Equals is used to achieve business logic
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", mark=" + mark + ", restMethod=" + restMethod + "]";
	}
}
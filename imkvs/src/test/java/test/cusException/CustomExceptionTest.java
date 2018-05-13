/**
 * 
 */
package test.cusException;

import org.junit.Test;

import com.imkvs.college.Student;
import com.imkvs.cusException.CustomNotFoundException;
import com.imkvs.cusException.CustomNullPointException;

/**
 * @author Karthikeyan
 *
 */
public class CustomExceptionTest {

	@Test
	public void CustomNotFoundExceptionTest() {
		Student st=new Student();
		st.setUserName("testuser");
		try {
			if(st.getUserName().equals("teststudent")) {				
				System.out.println("Student Existing.");
			}	else {
				throw new CustomNotFoundException("Student Not Found.");
			}
		}catch(CustomNotFoundException ex) {
			ex.getMessage();	
		}
	}

	@Test
	public void CustomNotFoundExceptTest() {
		Student st=new Student();
		st.setUserName("testuser");
		try {
			if(st.getUserName().equals("teststudent")) {
				System.out.println("Student Existing.");
			}	else {
				throw new CustomNotFoundException();
			}
		}catch(CustomNotFoundException ex) {
			ex.getMessage();	
		}
	}

	@Test
	public void CustomNullExceptionTest() {
		Student st=null;
		try {
			throw new CustomNullPointException("Student Object was Null."+st);
		}catch(CustomNullPointException ex) {
			ex.getMessage();	
		}
	}

	@Test
	public void CustomNullPointExceptTest() {
		@SuppressWarnings("unused")
		Student st=null;
		try {
			throw new CustomNullPointException();
		}catch(CustomNullPointException ex) {
			ex.getMessage();	
		}
	}
}
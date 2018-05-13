/**
 * 
 */
package test.college;

import org.junit.Test;

import com.imkvs.college.College;
import com.imkvs.college.Student;

/**
 * @author Karthikeyan
 *
 */
public class StudentTest {

	@Test
	public void studentTest() {

		String firstNam="firstName";
		String lastNam="lastName";
		String userNam="userName";
		int age=25;
		int mark=450;
		String method="GET";

		Student student=new Student(userNam,firstNam,lastNam,age,mark,method);
		student.getUserName();
		student.getFirstName();
		student.getLastName();
		student.getAge();
		student.getMark();
		student.getRestMethod();

		student.hashCode();
		student.toString();

		student.equals(student);
	}

	@Test
	public void studentNull() {
		String firstNam="firstName";
		String lastNam="lastName";
		String userNam="userName";
		int age=25;
		int mark=450;
		String method="GET";

		Student student=new Student(userNam,firstNam,lastNam,age,mark,method);
		Student student1=null;
		student.equals(student1);
	}

	@Test
	public void studentNullUser() {
		String firstNam="userName";
		String lastNam="lastName";
		String userNam="userName";
		int age=25;
		int mark=450;
		String method="GET";

		Student student=new Student(userNam,firstNam,lastNam,age,mark,method);
		Student student1=new Student("userNam",firstNam,lastNam,age,mark,method);
		student.equals(student1);
	}

	@Test
	public void studentOtherObj() {
		String firstNam="firstName";
		String lastNam="lastName";
		String userNam=null;
		int age=25;
		int mark=450;
		String method="GET";

		Student student=new Student(userNam,firstNam,lastNam,age,mark,method);
		College college=new College();
		student.equals(college);
	}
}
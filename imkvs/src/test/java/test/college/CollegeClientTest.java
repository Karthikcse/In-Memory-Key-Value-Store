/**
 * 
 */
package test.college;

import java.io.IOException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imkvs.college.College;
import com.imkvs.college.CollegeClient;
import com.imkvs.college.Section;
import com.imkvs.college.Student;

/**
 * @author Karthikeyan
 *
 */
public class CollegeClientTest {

	private static CollegeClient collegeClient;
	private static College college;

	private static int PORT_1=9000;
	private static int PORT_2=9002;
	private static int PORT_3=9003;

	@BeforeClass
	public static void setUp() {
		college=new College();
		college.serverOne();
		college.serverTwo();
		college.serverThree();
	}

	@Test
	public void sendMessageServer1() throws IOException {

		Student student=new Student();
		student.setUserName("arun");
		student.setFirstName("arun");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("PUT");	

		Student student2=new Student();
		student2.setUserName("david");
		student2.setFirstName("david");
		student2.setLastName("aaaa");
		student2.setAge(1);
		student2.setMark(100);
		student2.setRestMethod("PUT");	

		Section section=new Section();
		section.sendMessage(student.getUserName(), student);

		Section section2=new Section();
		section2.sendMessage(student2.getUserName(), student2);

		section.getAll(PORT_1, student2);

		collegeClient = new CollegeClient();

		Student student3=new Student();
		student3.setRestMethod("VALUES");
		collegeClient.sendMessage(student3, PORT_1);

		Student student4=new Student();
		student4.setUserName("david");
		student4.setRestMethod("GET");
		collegeClient.sendMessage(student4, PORT_1);

		Student student6=new Student();
		student6.setUserName("testaa");
		student6.setRestMethod("GET");
		collegeClient.sendMessage(student6,9005);
	}

	@Test
	public void sendMessageServer2() throws IOException {

		Student student=new Student();
		student.setUserName("mahesh");
		student.setFirstName("mahesh");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("PUT");	

		Student student2=new Student();
		student2.setUserName("naveen");
		student2.setFirstName("naveen");
		student2.setLastName("aaaa");
		student2.setAge(1);
		student2.setMark(100);
		student2.setRestMethod("PUT");	

		Section section=new Section();
		section.sendMessage(student.getUserName(), student);

		Section section2=new Section();
		section2.sendMessage(student2.getUserName(), student2);

		section.getAll(PORT_2, student2);

		collegeClient = new CollegeClient();

		Student student3=new Student();
		student3.setRestMethod("VALUES");
		collegeClient.sendMessage(student3, PORT_2);

		Student student4=new Student();
		student4.setUserName("naveen");
		student4.setRestMethod("GET");
		collegeClient.sendMessage(student4, PORT_2);

		Student student6=new Student();
		student6.setUserName("testaa");
		student6.setRestMethod("GET");
		collegeClient.sendMessage(student6,9005);
	}

	@Test
	public void sendMessageServer3() throws IOException {

		Student student=new Student();
		student.setUserName("xavior");
		student.setFirstName("xavior");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("PUT");	

		Student student2=new Student();
		student2.setUserName("sachin");
		student2.setFirstName("sachin");
		student2.setLastName("aaaa");
		student2.setAge(1);
		student2.setMark(100);
		student2.setRestMethod("PUT");	

		Section section=new Section();
		section.sendMessage(student.getUserName(), student);

		Section section2=new Section();
		section2.sendMessage(student2.getUserName(), student2);

		section.getAll(PORT_3, student2);

		collegeClient = new CollegeClient();

		Student student3=new Student();
		student3.setRestMethod("VALUES");
		collegeClient.sendMessage(student3, PORT_3);

		Student student4=new Student();
		student4.setUserName("sachin");
		student4.setRestMethod("GET");
		collegeClient.sendMessage(student4, PORT_3);

		Student student7=new Student();
		student7.setUserName("sachin");
		student7.setRestMethod("DELETE");
		collegeClient.sendMessage(student7,PORT_3);

		Student student6=new Student();
		student6.setUserName("testaa");
		student6.setRestMethod("GET");
		collegeClient.sendMessage(student6,9005);
	}

	@Test
	public void sererErrorTest() {
		Student student6=new Student();
		student6.setUserName("testaa");
		student6.setRestMethod("GETAll");
		collegeClient.sendMessage(student6,9005);
	}

	@After
	public void destroyTest() {
		college.destroy();
	}
}
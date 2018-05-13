/**
 * 
 */
package test.college;

import java.io.IOException;

import javax.servlet.ServletException;
import org.junit.Test;

import com.imkvs.college.College;
import com.imkvs.college.Student;

/**
 * @author Karthikeyan
 *
 */
public class CollegeTest {

	private College college;

	@Test
	public void putHtmlTest() throws ServletException, IOException{

		college=new College();

		Student student=new Student();
		student.setUserName("mahesh");
		student.setFirstName("mahesh");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("GET");

		college.putHtml(student.getUserName());
	}

	@Test
	public void homeHtmlTest() throws ServletException, IOException{
		college=new College();
		college.homeHtml();
	}

	@Test
	public void emptyListHtmlTest() throws ServletException, IOException{
		college=new College();

		Student student=new Student();
		student.setUserName("mahesh");
		student.setFirstName("mahesh");
		college.listEmptyHtml();
	}
	@Test
	public void getAllHtmlTest() throws ServletException, IOException{
		college=new College();

		Student student=new Student();
		student.setUserName("mahesh");
		student.setFirstName("mahesh");
		college.getAllHtml(student);
	}

	@Test
	public void getNotFoundHtmlTest() {
		college=new College();
		Student student=new Student();
		student.setUserName("mahesh");
		student.setRestMethod("GET");
		college.stdNotFoundHtml();
	}

	@Test
	public void processGetHtmlTest() {
		college=new College();

		Student student=new Student();
		student.setUserName("mahesh");
		student.setFirstName("mahesh");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("GET");

		college.processGetHtml(student);
	}

	@Test
	public void postHtmlTest() {
		college=new College();

		Student student=new Student();
		student.setUserName("mahesh");
		student.setFirstName("mahesh");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("POST");

		college.postHtml(student.getUserName());
	}

	@Test
	public void deleteHtmlTest() {
		college=new College();

		Student student=new Student();
		student.setUserName("mahesh");
		student.setRestMethod("DELETE");
		college.deleteHtml(student.getUserName());
	}

	@Test
	public void deleteNotFoundHtmlTest() {
		college=new College();
		Student student=new Student();
		student.setUserName("mahesh");
		student.setRestMethod("DELETE");
		college.studentNotFoundHtml();
	}

	@Test
	public void getAllStudentTest(){
		college=new College();
		college.getAllStudent();
	}

	@Test
	public void getStudentRequestTest() {
		college=new College();

		Student student=new Student();
		student.setUserName("mahesh");
		student.setFirstName("mahesh");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("POST");

		college.getStudentRequest(student.getUserName());
	}
}
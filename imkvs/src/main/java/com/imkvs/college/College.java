/**
 * 
 */
package com.imkvs.college;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imkvs.cusException.CustomNullPointException;
import com.imkvs.storedata.PrimaryKeyValueStore;

/**
 * @author Karthikeyan
 *
 */
@WebServlet("/college")
public class College extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private static Thread thread1,thread2,thread3=null;
	private static final String home="<a href=\"http://localhost:8080/imkvs/admin.html\">Home</a>";


	public void init(ServletConfig config) throws ServletException{
		//Starting Server 1
		serverOne();
		//Starting Server 2
		serverTwo();
		//Starting Server 3
		serverThree();
	}

	/*
	 * Used lambda Expressions for thread creation, its support Java 8 or higher version
	 */

	/*
	 * First Server Thread - 9000
	 */
	public void serverOne() {
		Runnable runnable1 = () -> {		   
			while(!Thread.currentThread().isInterrupted()) {
				new PrimaryKeyValueStore().startServer(9000);
			}		    	
		};
		thread1 = new Thread(runnable1);
		thread1.start();
	}

	/*
	 * Second Server Thread - 9001
	 */
	public void serverTwo() {
		Runnable runnable2 = () -> {		   
			while(!Thread.currentThread().isInterrupted()) {
				new PrimaryKeyValueStore().startServer(9001);
			}		    	
		};
		thread2 = new Thread(runnable2);
		thread2.start();
	}

	/*
	 * Third Server Thread - 9002
	 */
	public void serverThree() {
		Runnable runnable3 = () -> {		   
			while(!Thread.currentThread().isInterrupted()) {
				new PrimaryKeyValueStore().startServer(9002);
			}		    	
		};
		thread3 = new Thread(runnable3);
		thread3.start();
	}

	public void destroy() {
		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
	}
	public College() {

	}
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String userName=null;		
		userName=(String)request.getParameter("username");
		try {
			if(userName==null) {
				processGetAll(request,response);
			} else {
				processGet(response,userName);
				throw new CustomNullPointException("Entred Required Fileds.");
			}
		}
		catch(CustomNullPointException ex) {
			ex.getMessage();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/*
	 * Get all student list based on port (9000,9001 or 9002)
	 */
	private void processGetAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String portString=request.getParameter("port");
		if(portString==null) {
			return;
		}

		Integer port=Integer.parseInt(portString);

		Student student=getAllStudent();
		Set<Student> setOfStudent=new Section().getAll(port,student);

		PrintWriter writer=response.getWriter();
		StringBuilder sb=new StringBuilder();

		if(setOfStudent.isEmpty() || setOfStudent==null) {			
			String htmlResponse=listEmptyHtml();
			writer.println(htmlResponse);
		} else {
			for(Student e:setOfStudent) {
				String htmlResponse=getAllHtml(e);
				sb.append(htmlResponse);
			}
			String htmlResponse=homeHtml();
			sb.append(htmlResponse);
			writer.println(sb);
		}
	}
	
	public Student getAllStudent() {
		Student student=new Student();
		student.setRestMethod("VALUES");
		return student;
	}
	/*
	 * Get Student details from in-memory based on username
	 */
	private void processGet(HttpServletResponse response, String userName) throws IOException{
		try {
			Student student = getStudentRequest(userName.toLowerCase());
			Set<Student> setOfStudent = new Section().sendMessage(userName,student);

			if(setOfStudent.isEmpty()) {			
				String htmlResponse =stdNotFoundHtml();
				PrintWriter writer = response.getWriter();
				writer.println(htmlResponse);
			} else {
				Student entity = setOfStudent.iterator().next();
				PrintWriter writer =response.getWriter();
				StringBuilder sb = new StringBuilder();
				String htmlResponse =processGetHtml(entity);
				sb.append(htmlResponse);
				writer.println(sb);
			}			
		}
		catch(Exception ex) {
			new CustomNullPointException("Getting Studes Deatils Failed :: "+ex);
		}
	}

	public Student getStudentRequest(String userName) {
		Student student = new Student();
		student.setUserName(userName);
		student.setRestMethod("GET");
		return student;
	}
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException{
		String userName = "";
		String firstname ="";
		String delete ="";	

		userName = (String)request.getParameter("username");
		firstname = (String)request.getParameter("firstname");
		delete = (String)request.getParameter("delete");

		if(delete != null) {
			if(delete.equals("true")) {
				delete(response,userName);
			}else {
				try {
					PrintWriter writer = response.getWriter();
					String htmlResponse = postHtml(userName);
					writer.println(htmlResponse);
					throw new CustomNullPointException("Getting IO Exception For doPost Method.");
				}catch(Exception ex) {
					ex.printStackTrace();				
				}
			}
		}else {
			put(request,response,userName,firstname);
		}
	}
	/*
	 * Used to Delete the Student Details Form the In-Memory based on given UserName
	 */
	private void delete(HttpServletResponse response, String username){
		Student employee=null;
		try{
			employee = new Student();
			if(!username.isEmpty()) {
				employee.setUserName(username.toLowerCase());
				employee.setRestMethod("DELETE");

				Set<Student> setOfEmplyoee = new Section().sendMessage(username, employee);
				PrintWriter writer = response.getWriter();
				if(setOfEmplyoee.isEmpty()) {
					String htmlResponse =studentNotFoundHtml();
					writer.println(htmlResponse);
				}else {
					StringBuilder sb = deleteHtml(username);
					writer.println(sb);
				}
			}			 
		}catch(Exception ex) {
			new CustomNullPointException("Please Enter Required Fields."+ex);
		}
	}
	/*
	 * Save Student details to in-memeory using put method
	 */
	private void put(HttpServletRequest request, HttpServletResponse response, String userName, String firstname){
		Student student = null;
		try {
			student = new Student();

			student.setUserName(userName);
			student.setFirstName(firstname);
			student.setLastName(request.getParameter("lastname"));
			student.setAge(Integer.parseInt(request.getParameter("age")));
			student.setMark(Integer.parseInt(request.getParameter("mark")));
			student.setRestMethod("PUT");

			new Section().sendMessage(userName, student);

			PrintWriter writer = response.getWriter();
			StringBuilder sb =putHtml(userName);
			writer.println(sb);
		}catch(Exception ex) {
			throw new CustomNullPointException("Creating Studend getting Failed"+ex);
		}
	}
	/*
	 * HTML Template for servlet response
	 */
	public String listEmptyHtml() {
		String htmlResponse="<html>";
		htmlResponse+="Student list is empty at specified port.<br>";
		htmlResponse +=home;
		htmlResponse+="</html>";
		return htmlResponse;
	}

	public String homeHtml(){
		String htmlResponse="<html>";
		htmlResponse +=home;
		htmlResponse+="</html>";
		return htmlResponse;
	}

	public String getAllHtml(Student e) {
		String htmlResponse="<html>";
		htmlResponse+="FirstName is : "+e.getFirstName()+"</br>";
		htmlResponse+="UserName is : "+e.getUserName()+"</br>";
		htmlResponse+="-----------------------------------</br>";
		htmlResponse+="</html>";
		return htmlResponse;
	}

	public String stdNotFoundHtml() {
		String htmlResponse = "<html>";
		htmlResponse += "Student not found <br/>";
		htmlResponse +=home;
		htmlResponse +="</html>";
		return htmlResponse;
	}

	public String processGetHtml(Student entity) {		
		String htmlResponse = "<html>";
		htmlResponse += "Username is:" + entity.getUserName() + "<br/>";
		htmlResponse += "Firstname is:" + entity.getFirstName() + "<br/>";
		htmlResponse += "Lastname is:" + entity.getLastName() + "<br/>";
		htmlResponse += "Age is:" + entity.getAge() + "<br/>";
		htmlResponse += "Mark is:" + entity.getMark() + "<br/>";			
		htmlResponse += home;
		htmlResponse +="</html>";
		return htmlResponse;		
	}

	public String postHtml(String userName) {
		String htmlResponse = "<html>";
		htmlResponse += "Student " + userName + " will only be deleted when you hit confirm = true. <br/>";
		htmlResponse += home;
		htmlResponse +="</html>";
		return htmlResponse;
	}

	public String studentNotFoundHtml() {
		String htmlResponse = "<html>";
		htmlResponse += "Student to be deleted not found. <br/>";
		htmlResponse += home;
		htmlResponse +="</html>";
		return htmlResponse;
	}

	public StringBuilder deleteHtml(String username) {
		StringBuilder sb = new StringBuilder();

		String htmlResponse = "<html>";
		htmlResponse += "Student " + username + " has been deleted. <br/>";
		htmlResponse += home;
		htmlResponse +="</html>"; 
		sb.append(htmlResponse);
		return sb;
	}

	public StringBuilder putHtml(String userName) {
		StringBuilder sb = new StringBuilder();

		String htmlResponse = "<html>";
		htmlResponse += "Student " + userName + " has been added to the cache. <br/>";
		htmlResponse += home;
		htmlResponse +="</html>"; 
		sb.append(htmlResponse);
		return sb;
	}
}
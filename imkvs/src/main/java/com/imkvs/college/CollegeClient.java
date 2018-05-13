/**
 * 
 */
package com.imkvs.college;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

/**
 * @author Karthikeyan
 *
 */
public class CollegeClient {
	
	Socket clientSocket=null;
	ObjectOutputStream outToServer=null;
	ObjectInputStream fromServer=null;
	/*
	 * Client Socket host "127.0.0.0" and port 'll be based on request thread, either 9000,9001,9002  
	 * It will create new client
	 */
	public Set<Student> sendMessage(Student student, int port) {

		try {
			clientSocket=new Socket("127.0.0.1", port);	
			outToServer=new ObjectOutputStream(clientSocket.getOutputStream());
			outToServer.writeObject(student);
			fromServer=new ObjectInputStream(clientSocket.getInputStream());
			@SuppressWarnings("unchecked")
			Set<Student> employeeSet=(Set<Student>) fromServer.readObject();
			return employeeSet;
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			if(clientSocket!=null) {
				try {
					clientSocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(fromServer!=null) {
				try {
					fromServer.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(outToServer!=null) {
				try {
					outToServer.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
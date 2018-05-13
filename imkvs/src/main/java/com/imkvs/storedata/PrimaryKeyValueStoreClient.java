/**
 * 
 */
package com.imkvs.storedata;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

import com.imkvs.college.Student;

/**
 * @author Karthikeyan
 *
 */
public class PrimaryKeyValueStoreClient implements Runnable{

	private Socket clientSocket;
	private Set<Student> studentSet;

	public PrimaryKeyValueStoreClient(Socket clientSocket, Set<Student> studentSet) {
		this.clientSocket=clientSocket;
		this.studentSet=studentSet;
	}
	
	public void run() {
		ObjectOutputStream outputStream=null;
		try {
			outputStream=new ObjectOutputStream(clientSocket.getOutputStream());
			outputStream.writeObject(studentSet);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
/**
 * 
 */
package com.imkvs.storedata;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Set;

import com.imkvs.college.Student;

/**
 * @author Karthikeyan
 *
 */
public class PrimaryKeyValueStore {

	private InMemoryCache inMemoryCache;

	public PrimaryKeyValueStore() {
		inMemoryCache=new InMemoryCache();
	}

	/*
	 * Socket Server
	 */
	public void startServer(int port) {
		ServerSocket serverSocket=null;
		ObjectInputStream inputStreamFromClient=null;
		Socket clientSocket=null;

		try {
			serverSocket=new ServerSocket(port);
			while (true) {
				clientSocket=serverSocket.accept();

				inputStreamFromClient=new ObjectInputStream(clientSocket.getInputStream());
				Student student=(Student) inputStreamFromClient.readObject();

				switch(student.getRestMethod()) {

				case "PUT": inMemoryCache.put(student.getUserName(),student);
				new Thread(new PrimaryKeyValueStoreClient(clientSocket,Collections.emptySet())).start();
				break;

				case "GET": new Thread(new PrimaryKeyValueStoreClient(clientSocket,inMemoryCache.get(student.getUserName()))).start();
				break;

				case "DELETE": Set<Student> setStudent=inMemoryCache.get(student.getUserName());
				inMemoryCache.delete(student.getUserName());

				new Thread(new PrimaryKeyValueStoreClient(clientSocket,setStudent)).start();
				break;

				case "VALUES": new Thread(new PrimaryKeyValueStoreClient(clientSocket,inMemoryCache.getAll())).start();
				break;

				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(inputStreamFromClient!=null) {
				try {
					inputStreamFromClient.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(serverSocket!=null) {
				try {
					serverSocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
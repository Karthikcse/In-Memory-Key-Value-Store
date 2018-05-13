/**
 * 
 */
package com.imkvs.storedata;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.imkvs.college.Student;

/**
 * @author Karthikeyan
 *
 */
public class InMemoryCache {

	/*
	 * Store, retrieve and delete data from in-memory cache
	 */

	private Map<String, Student> cache;

	public InMemoryCache() {
		cache=new HashMap<String, Student>();
	}
	/*
	 * Save Student data
	 */
	public synchronized void put(final String userName,final Student student) {
		cache.put(userName.toLowerCase(), student);
	}
	/*
	 * Retrieve Student data based on given username
	 */
	public synchronized Set<Student> get(final String userName) {
		if(cache.containsKey(userName.toLowerCase())) {
			Set<Student> set=new HashSet<Student>();
			set.add(cache.get(userName));
			return set;		
		}
		return Collections.emptySet();
	}
	/*
	 * Delete Student data based on given username
	 */
	public synchronized void delete(final String userName) {
		if(cache.containsKey(userName)) {
			cache.remove(userName);
		}
	}
	/*
	 * Retrieve all Student data
	 */
	public synchronized Set<Student> getAll() {
		if(!cache.isEmpty()) {
			Set<Student> s=new HashSet<Student>();
			s.addAll(cache.values());
			return s;
		}
		return Collections.emptySet();
	}
}
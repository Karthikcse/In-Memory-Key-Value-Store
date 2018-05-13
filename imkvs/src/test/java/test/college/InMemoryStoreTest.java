/**
 * 
 */
package test.college;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.imkvs.college.Student;
import com.imkvs.storedata.InMemoryCache;

/**
 * @author Karthikeyan
 *
 */
public class InMemoryStoreTest {

	@Test
	public void inMemoryPutTest() {

		Student student=new Student();
		student.setUserName("a");
		student.setFirstName("aa");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("POST");

		Student student2=new Student();
		student2.setUserName("b");
		student2.setFirstName("bb");
		student2.setLastName("bbb");
		student2.setAge(2);
		student2.setMark(200);
		student2.setRestMethod("POST");

		InMemoryCache inMemoryCache=new InMemoryCache();

		inMemoryCache.put(student.getUserName(),student);
		inMemoryCache.put(student.getUserName(),student2);
	}

	@Test
	public void inMemoryGetTest() {

		Student student=new Student();
		student.setUserName("a");
		student.setFirstName("aa");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("POST");

		Student student2=new Student();
		student2.setUserName("b");
		student2.setFirstName("bb");
		student2.setLastName("bbb");
		student2.setAge(2);
		student2.setMark(200);
		student2.setRestMethod("POST");

		InMemoryCache inMemoryCache=new InMemoryCache();

		inMemoryCache.put(student.getUserName(),student);
		inMemoryCache.put(student.getUserName(),student2);

		inMemoryCache.get("a");	
		inMemoryCache.get("c");
	}

	@Test
	public void inMemoryGetAllTest() {

		Student student=new Student();
		student.setUserName("a");
		student.setFirstName("aa");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("POST");

		Student student2=new Student();
		student2.setUserName("b");
		student2.setFirstName("bb");
		student2.setLastName("bbb");
		student2.setAge(2);
		student2.setMark(200);
		student2.setRestMethod("POST");

		InMemoryCache inMemoryCache=new InMemoryCache();

		inMemoryCache.put(student.getUserName(),student);
		inMemoryCache.put(student.getUserName(),student2);

		assertNotNull("Student Details exist.",inMemoryCache.getAll());
	}

	@Test
	public void inMemoryDeleteTest() {

		Student student=new Student();
		student.setUserName("a");
		student.setFirstName("aa");
		student.setLastName("aaa");
		student.setAge(1);
		student.setMark(100);
		student.setRestMethod("POST");

		Student student2=new Student();
		student2.setUserName("b");
		student2.setFirstName("bb");
		student2.setLastName("bbb");
		student2.setAge(2);
		student2.setMark(200);
		student2.setRestMethod("POST");

		InMemoryCache inMemoryCache=new InMemoryCache();

		inMemoryCache.put(student.getUserName(),student);
		inMemoryCache.put(student.getUserName(),student2);

		inMemoryCache.delete("b");
		inMemoryCache.delete("c");
	}
}

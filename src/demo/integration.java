package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

class integration {
	
	private Vector <Student> studentList = new Vector<Student>();
	private double weighted[] = {0.1,0.1,0.1,0.3,0.4};
	private String filename = "/test.txt";
	ByteArrayInputStream testIn;
	ByteArrayOutputStream testOut;
	GradeSystem TestGradeSys;
	
	@BeforeEach
	void setUp() throws Exception {
		testIn = new ByteArrayInputStream("?".getBytes());
		System.setIn(testIn);
		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
		TestGradeSys = new GradeSystem();
		weighted[0] = 0.1;
		weighted[1] = 0.1;
		weighted[2] = 0.1;
		weighted[3] = 0.3;
		weighted[4] = 0.4;
		TestGradeSys.InputData(studentList, weighted, filename);
	}
    
	@AfterEach
	void tearDown() throws Exception {
		studentList.clear();
	}

	
	@Test
	public void test() {
		// checkID
		assertEquals(1, TestGradeSys.checkID(955002056, studentList));
		
		// 
	}
}
	
	
	
	
	
	
	
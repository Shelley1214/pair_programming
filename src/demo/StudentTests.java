package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTests {
	
	private Vector <Student> studentList = new Vector<Student>();
	private double weighted[] = {0.1,0.1,0.1,0.3,0.4};
	private String filename = "/test.txt";
	
	@BeforeEach
	void setUp() throws Exception {
		GradeSystem TestGradeSys = new GradeSystem();
		TestGradeSys.InputData(studentList, weighted, filename);
		System.out.println("===========Before test ============");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("========== Before test ============");
	}

	@Test
	public void checkIdTestPass() {
		GradeSystem TestGradeSys = new GradeSystem();
		assertEquals(0, TestGradeSys.checkID(955002056, studentList));
		System.out.println("========== pass ============");
	}
	

}

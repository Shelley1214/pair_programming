package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTests {
	GradeSystem TestGradeSystem = new GradeSystem();
	Vector<Student> studentList = new Vector<Student>();
	double weighted[] = {0.1,0.1,0.1,0.3,0.4};
	String filename = "/test.txt";
	
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("===========Before test============");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("========== Before test============");
	}

	@Test
	public void checkIdTestPass() {
//		assertEquals(,TestGradeSystem.InputData(studentList,weighted,filename));
//		assertEquals(10, TestGradeSys.checkID(3, 7));
	}
	
	@Test
	public void checkIdTestFail() {
	}

}

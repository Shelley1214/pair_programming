package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegratedTest_1 {

	private Vector <Student> studentList = new Vector<Student>();
	private double weighted[] = {0.1,0.1,0.1,0.3,0.4};
	private String filename = "/test.txt";
	ByteArrayInputStream testIn;
	ByteArrayOutputStream testOut;
	
	@BeforeEach
	void setUp() throws Exception {
		GradeSystem TestGradeSys = new GradeSystem();
		TestGradeSys.InputData(studentList, weighted, filename);
		weighted[0] = 0.1;
		weighted[1] = 0.1;
		weighted[2] = 0.1;
		weighted[3] = 0.3;
		weighted[4] = 0.4;
		System.out.println("===========Before test ============");
	}
    
	@AfterEach
	void tearDown() throws Exception {
		studentList.clear();
		System.out.println("========== After test ============");
	}

	@Test
	void test() {
		
	}

}

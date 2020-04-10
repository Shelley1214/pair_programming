package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

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
		System.out.println("========== After test ============");
	}

	@Test
	public void checkIdTestPass() {
		System.out.println("checkID_1");
		GradeSystem TestGradeSys = new GradeSystem();
		assertEquals(0, TestGradeSys.checkID(955002056, studentList));
	}
	
	@Test
	public void checkIdTestFail() {
		System.out.println("checkID_2");
		GradeSystem TestGradeSys = new GradeSystem();
		assertEquals(-1, TestGradeSys.checkID(0, studentList));
	}
	
	@Test
	public void changeWeightTestPass() throws Exception {
		System.out.println("changeWeight_1");
		GradeSystem TestGradeSys = new GradeSystem();
		double test_weighted[] = {0.2,0.2,0.2,0.2,0.2};
		String input = "​lab1 20 lab2 20 lab3 20 mid-term 20 final exam 20";
		
		TestGradeSys.changeWeight(weighted, studentList);
		if(!weighted.equals(test_weighted)) throw new Exception("result wrong!");
	}
	

}

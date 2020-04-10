package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

class integration {
	// 975002021 楊祺賢 81 97 90 82 84 
	private Vector <Student> studentList = new Vector<Student>();
	private double weighted[] = {0.1,0.1,0.1,0.3,0.4};
	private String filename = "/input.txt";
	ByteArrayInputStream testIn;
	ByteArrayOutputStream testOut;
	GradeSystem TestGradeSys = new GradeSystem();;
	
	@BeforeEach
	void setUp() throws Exception {

	}
    
	@AfterEach
	void tearDown() throws Exception {
		studentList.clear();
	}
	/**
	* test function: GradeSystem.checkID(), GradeSystem.showGrade(), GradeSystem.showValue(), GradeSystem.showRank(), GradeSystem.changeWeight(), GradeSystem.Exit()
	* test data: checkID 975002021
	* test data: show_grade (idx 6)
	* test data: show_value (idx 6)
	* test data: chang_weight (0.2, 0.2, 0.2, 0.2, 0.2)
	* test data: Exit
	* time consuming: 0.121s
	*/
	@Test
	public void test()throws Exception  {
		TestGradeSys.InputData(studentList, weighted, filename);
		// showGrade
		int position = TestGradeSys.checkID(975002021, studentList);
		assertEquals("81 97 90 82 84", TestGradeSys.showGrade(studentList, position));
		
		// averageValue
		assertEquals("85.00", TestGradeSys.showValue(studentList, position));
		
		//changeweight
		double test_weighted[] = {0.2,0.2,0.2,0.2,0.2};
		String input = "lab1 20 lab2 20 lab3 20 mid-term 20 final exam 20 Y";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertArrayEquals(test_weighted, TestGradeSys.changeWeight(weighted, studentList));
		
		// averageValue
		assertEquals("86.80", TestGradeSys.showValue(studentList, position));
		
		//Rank
		assertEquals(53, TestGradeSys.showRank(studentList, 975002021));
		
		//Exit
		input = "Q";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertEquals(1, TestGradeSys.Exit(TestGradeSys, studentList, 0));
		
	}
}
	
	
	
	
	
	
	
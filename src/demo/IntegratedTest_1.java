package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegratedTest_1 {
	GradeSystem TestGradeSys = new GradeSystem();
	private Vector <Student> studentList = new Vector<Student>();
	private double weighted[] = {0.1,0.1,0.1,0.3,0.4};
	ByteArrayInputStream testIn;
	ByteArrayOutputStream testOut;
	
	@BeforeEach
	void setUp() throws Exception {
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
		TestGradeSys = null;
		System.out.println("========== After test ============");
	}
	/**
	 * test function: TestGradeSys.InputData(), TestGradeSys.InputData().showRank(), TestGradeSys.showGrade(), TestGradeSys.showValue(),
	 * TestGradeSys.changeWeight, TestGradeSys.Exit()
	 * test data : Id->962001044
	 * test data : Grade->87 86 98 88 87
	 * test data : weight->"lab1 30 lab2 30 lab3 10 mid-term 20 final exam 10 Y"
	 * test data : Exit->"Q"
	 * Time consuming: 0.024s
	 */

	@Test
	void test() throws Exception{
		int targetId = 962001044; //962001044 凌宗廷 87 86 98 88 87
		
		//Test inputData
		assertEquals(1,TestGradeSys.InputData(studentList, weighted, "/test.txt")); 
		
		//Test showRank
		assertEquals(1, TestGradeSys.showRank(studentList, targetId));
		
		//Test showGrade
		int position = TestGradeSys.checkID(targetId, studentList);
		assertEquals("87 86 98 88 87", TestGradeSys.showGrade(studentList, position));
		
		//Test Value
		assertEquals("88.30", TestGradeSys.showValue(studentList, position));
		
		//Test changeWeight
		double test_weighted[] = {0.3,0.3,0.1,0.2,0.1};
		String input = "lab1 30 lab2 30 lab3 10 mid-term 20 final exam 10 Y";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertArrayEquals(test_weighted, TestGradeSys.changeWeight(weighted, studentList));
		
		//Test Value
		assertEquals("88.00", TestGradeSys.showValue(studentList, position));
		
		//Test Rank
		assertEquals(1, TestGradeSys.showRank(studentList, targetId));
		
		//Test Exit
		input = "Q";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertEquals(1, TestGradeSys.Exit(TestGradeSys, studentList, 0));
	}

}

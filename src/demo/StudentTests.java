package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

class StudentTests {
	GradeSystem TestGradeSys = new GradeSystem();
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
		TestGradeSys = null;
		System.out.println("========== After test ============");
	}
	/**
	 * test function: GradeSystem.checkID()
	 * test data: 955002056
	 * Time consuming: 0.003s
	 */
	@Test
	public void checkIdTestPass() {
		assertEquals(1, TestGradeSys.checkID(955002056, studentList));
		System.out.println("checkID_1");
	}
	/**
	 * test function: GradeSystem.checkID()
	 * test data: 0
	 * Time consuming: 0.002s
	 */
	@Test
	public void checkIdTestFail() {
		assertEquals(-1, TestGradeSys.checkID(0, studentList));
		System.out.println("checkID_2");
	}
	/**
	 * test function: GradeSystem.showRank()
	 * test data: 
	 * ID:962001044.0
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * Time consuming: 0.003s
	 */
	@Test
	public void showRankTestPass() {
		assertEquals(1, TestGradeSys.showRank(studentList, 962001044.0));
		System.out.println("showRank_1");
	}
	/**
	 * test function: GradeSystem.showRank()
	 * test data: 
	 * ID:962001051.0
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * Time consuming: 0.002s
	 */
	@Test
	public void showRankTestFail() {
		assertEquals(2, TestGradeSys.showRank(studentList, 962001051.0));
		System.out.println("showRank_2");
	}
	/**
	 * test function: GradeSystem.showGrade()
	 * test data: 
	 * ID:955002056
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * Time consuming: 0.012s
	 */
	@Test
	public void showGradeTestPass() {
		int position = TestGradeSys.checkID(955002056, studentList);
		assertEquals("88 92 88 98 91", TestGradeSys.showGrade(studentList, position));
		System.out.println("showGrade_1");
	}
	/**
	 * test function: GradeSystem.showGrade()
	 * test data: 
	 * ID:962001044
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * Time consuming: 0.002s
	 */
	@Test
	public void showGradeTestFail() {
		int position = TestGradeSys.checkID(962001044, studentList);
		assertEquals("87 86 98 88 87", TestGradeSys.showGrade(studentList, position));
		System.out.println("showGrade_2");
	}
	/**
	 * test function: GradeSystem.showValue()
	 * test data: 
	 * ID:962001044
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * Time consuming: 0.002s
	 */
	@Test
	public void showValueTestPass() {
		int position = TestGradeSys.checkID(962001044, studentList);
		assertEquals("88.30", TestGradeSys.showValue(studentList, position));
		System.out.println("showValue_1");
	}
	/**
	 * test function: GradeSystem.showValue()
	 * test data: 
	 * ID:955002056
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * Time consuming: 0.004s
	 */
	@Test
	public void showValueTestFail() {
		int position = TestGradeSys.checkID(955002056, studentList);
		assertEquals("92.60", TestGradeSys.showValue(studentList, position));
		System.out.println("showValue_1");
	}
	/**
	 * test function: GradeSystem.changeWeight()
	 * test data : "lab1 10 lab2 20 lab3 30 mid-term 20 final exam 20 W Y"
	 * Time consuming: 0.013s
	 */
	@Test
	public void changeWeightTestFail() throws Exception {
		double test_weighted[] = {0.1,0.2,0.3,0.2,0.2};
		String input = "lab1 10 lab2 20 lab3 30 mid-term 20 final exam 20 W Y";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertArrayEquals(test_weighted, TestGradeSys.changeWeight(weighted, studentList));
		System.out.println("changeWeight_2");
	}
	/**
	 * test function: GradeSystem.changeWeight()
	 * test data : "lab1 20 lab2 20 lab3 20 mid-term 20 final exam 20 Y"
	 * Time consuming: 0.006s
	 */
	@Test
	public void changeWeightTestPass() throws Exception {
		double test_weighted[] = {0.2,0.2,0.2,0.2,0.2};
		String input = "lab1 20 lab2 20 lab3 20 mid-term 20 final exam 20 Y";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertArrayEquals(test_weighted, TestGradeSys.changeWeight(weighted, studentList));
		System.out.println("changeWeight_1");
	}
	
	/**
	 * test function: GradeSystem.Exit()
	 * test data: Q
	 * @throws IOException
	 * Time consuming: 0.003s
	 */
	@Test
	public void ExitPass() throws Exception {
		String input = "Q";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertEquals(1, TestGradeSys.Exit(TestGradeSys, studentList, 0));
		System.out.println("Exit_1");
	}
	
	/**
	 * test function: GradeSystem.Exit()
	 * test data: 955002056
	 * @throws IOException
	 * Time consuming: 0.028s
	 */
	@Test
	public void ExitFail() throws Exception {
		String input = "955002056";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
	    testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
		assertEquals(0, TestGradeSys.Exit(TestGradeSys, studentList, 0));
		System.out.println("Exit_1");
	}
	
	
	/**
	 * test function: GradeSystem.inputData()
	 * test data: /test.txt
	 * @throws IOException
	 * Time consuming: 0.004s
	 */
	@Test
	public void InputDataPass() throws IOException {
		assertEquals(1,TestGradeSys.InputData(studentList, weighted, "/test.txt")); 
		System.out.println("InputData_1");
	}
	/**
	 * test function: GradeSystem.inputData()
	 * test data: /wrong.txt
	 * @throws IOException
	 * Time consuming: 0.003s
	 */
	@Test
	public void InputDataFail() throws IOException {
		assertEquals(0,TestGradeSys.InputData(studentList, weighted, "/wrong.txt")); 
		System.out.println("InputData_2");
	}
	

	/**
	 * test function: GradeSystem.calValue()
	 * test data: 
	 * ID:955002056
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * weight: 0.3, 0.3, 0.1, 0.2, 0.2
	 * Time consuming: 0.002s
	 */
	@Test
	public void calValueTestPass() {
		int position = TestGradeSys.checkID(955002056, studentList);
		weighted[0] = 0.3;
		weighted[1] = 0.3;
		weighted[2] = 0.1;
		weighted[3] = 0.2;
		weighted[4] = 0.1;
		assertEquals("91.50", TestGradeSys.calValue(studentList, weighted, position));
		System.out.println("calValue_1");
	}
	/**
	 * test function: GradeSystem.calValue()
	 * test data: 
	 * ID:955002056
	 * studentList: 
	 * {[ID:962001051, name:李威廷, lab1:81, lab2:32, lab3:50, mid-term:90, final_exam: 93],
	 * 	[ID:955002056, name:許文馨, lab1:88, lab2:92, lab3:88, mid-term:98, final_exam: 91],
	 * 	[ID:962001044, name:凌宗廷, lab1:87, lab2:86, lab3:98, mid-term:88, final_exam: 87]}
	 * weight: 0.5, 0.1, 0.1, 0.1, 0.2
	 * Time consuming: 0.003s
	 */
	@Test
	public void calValueTestFail() {
		int position = TestGradeSys.checkID(962001044, studentList);
		weighted[0] = 0.5;
		weighted[1] = 0.1;
		weighted[2] = 0.1;
		weighted[3] = 0.1;
		weighted[4] = 0.2;
		assertEquals("88.10", TestGradeSys.calValue(studentList, weighted, position));
		System.out.println("calValue_2");
	}

}

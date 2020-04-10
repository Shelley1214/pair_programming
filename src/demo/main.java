package demo;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class main {
	static double currentId = -1;
	public static void main(String args[]) throws IOException{
		main A = new main();
		Vector<Student> studentList = new Vector<Student>();
		double weighted[] = {0.1,0.1,0.1,0.3,0.4};
		A.InputData(studentList, weighted);
		System.out.print("頛詨ID��� Q (蝯�蝙�)?:\n");
		Scanner scanner = new Scanner(System.in);
		int position = A.Input(studentList, scanner); 		
		while (position == -1) { 			
			System.out.print("頛詨ID��� Q (蝯�蝙�)?:\n"); 			
			position = A.Input(studentList, scanner);
			// Not exist 		} 		
			if (position == -2) return; //Q
		}
		A.inputCommand(scanner, studentList, weighted, position, A);
	}
	
	public void inputCommand(Scanner scanner, Vector<Student> studentList, double weighted[], int position, main A) {
		while(true) {
			System.out.print("頛詨��誘\n1) G憿舐內��蜀 \n2) R憿舐內���� \n3) A憿舐內撟喳�� \n4) W������ \n5) E����\n");
			String num1 = scanner.next();
			if(num1.contentEquals("G")) {
				studentList.get(position).showGrade();
			}else if(num1.contentEquals("R")) {
				Collections.sort(studentList);
				position = checkID(currentId, studentList);
				System.out.println(studentList.get(position).name + "���� : " + position);
			}else if(num1.contentEquals("A")) {
				studentList.get(position).showValue();
			}else if(num1.contentEquals("W")) {
				weighted = A.changeWeight(weighted, studentList, position, scanner);
			}else if(num1.contentEquals("E")) {
				if(A.Exit(A, studentList, scanner, position) == 1) {
					return;
				}
			}else {
				System.out.print("Wrong input!\n");
			}
		}
	}
	
	public void inputWeight(double weighted[], Scanner scanner) {
		for(int i=0; i<5; i++) {
			String num1 = scanner.next();
			if(num1.contentEquals("lab1")) weighted[0] = scanner.nextDouble()/100;
			if(num1.contentEquals("lab2")) weighted[1] = scanner.nextDouble()/100;
			if(num1.contentEquals("lab3")) weighted[2] = scanner.nextDouble()/100;
			if(num1.contentEquals("mid-term")) weighted[3] = scanner.nextDouble()/100;
			if(num1.contentEquals("final")) {
				num1 = scanner.next();
				if(num1.contentEquals("exam")) weighted[4] = scanner.nextDouble()/100;
			}
		}
	}
	
	public void printWeight(double weighted[], int isOld) {
		if(isOld == 1) {
			System.out.println("�����ab1 " + weighted[0]*100 + "% " + "lab2 " + weighted[1]*100 + "% " +
					"lab3 " + weighted[2]*100 + "% " + "mid-term " + weighted[3]*100 + "% " + 
					"final exam " + weighted[4]*100 + "%" + "\n" + "隢撓�����:");
		}else {
			System.out.println("隢Ⅱ隤����: lab1 " + weighted[0]*100 + "% " + "lab2 " + weighted[1]*100 + "% " +
					"lab3 " + weighted[2]*100 + "% " + "mid-term " + weighted[3]*100 + "% " + 
					"Final Exam " + weighted[4]*100 + "% 隞乩�迤蝣箏��? Y (Yes) ���� N (No)");
		}
	}
	
	/*
	 * when console get "W" command
	 *  
	 * @param studentList = data	 
	 * @param weighted = each weighted value
	 * @param position = student's vector position
	 * @param scanner = scan console
	 * 
	 * @return reweighted value
	 * 		   
	 * Example: input lab1 20 lab2 20 lab3 20 mid-term 20 final exam 20
	 *          weighted[5] = {0.2, 0.2, 0.2, 0.2, 0.2}
	 *          
	 * Time estimate: O(1)
	 */
	
	public double[] changeWeight(double weighted[], Vector<Student> studentList, int position, Scanner scanner) {
		printWeight(weighted, 1);
		while(true) {
			inputWeight(weighted, scanner);
			printWeight(weighted, 0);
			String check = scanner.next();
			while(!check.contentEquals("Y") && !check.contentEquals("N")) {
				System.out.printf("wrong input!隢�頛詨!\n Y (Yes) ���� N (No)\n");
				check = scanner.next();
			}
			if(check.contentEquals("Y")) break;
		}
		for(int i=0; i<studentList.size(); i++) {
			studentList.get(i).calValue(weighted);
		}
		return weighted;
	}
	/*
	 * when console get "E" command
	 *  
	 * @param A = main
	 * @param studentList = data
	 * @param scanner = scan console
	 * 
	 * @return 0 -> another student ID
	 * 		   1 -> quit
	 * 
	 * Example: input Q, return 1, and then exist
	 * Time estimate: O(1)
	 */
	public int Exit(main A, Vector<Student> studentList, Scanner scanner, int position) {
		System.out.print("頛詨�D���� Q (���蝙���)?"); 
		int exitFlag = 0;
		position = A.Input(studentList, scanner); 				
		while (position == -1) 
			position = A.Input(studentList, scanner); // Not exist 				
		if (position == -2) { 
			exitFlag = 1;
		}
		return exitFlag;
	}
	
	/*
	 * when console get "Q" or an ID
	 *  
	 * @param studentList = data
	 * @param scanner = scan console
	 * 
	 * @return -2 -> quit
	 * 		   or student's vector position
	 * 
	 * Example: input 955002056 , return position 0 (studentList[0])
	 * Time estimate: O(checkID()) -> O(n)
	 */
	public int Input(Vector<Student> studentList, Scanner scanner) {
		// Q or input ID
		String num1 = scanner.next();
		double num;
		if(num1.contentEquals("Q")) {
			System.out.print("蝯��n");
			return -2;
		}else {
			num = Double.parseDouble(num1);
			currentId = num;
		}
		
		int position = checkID(num,studentList);
		if(position != -1) {
			System.out.printf("Welcome, %s\n", studentList.get(position).name);
		}
		else {
			System.out.print("�甇支犖�!隢�頛詨\n");
		}
		return position;
	}
	
	/*
	 * input the data from the text file
	 * @param studentList = data
	 * @param weighted = weighted data (init = 0.1,0.1,0.1,0.3,0.4)
	 * @return -
	 * @throws NoSuchFileExceptions
	 * 			if File is not exist, throw Exceptions
	 * 
	 * Example :　input.txt -> 975002070 楊宗穎 93 93 97 96 94
	 * 			 studentList[] = { id = 975002070, name = 楊宗穎, 
	 * 							   lab1 = 93, lab2 = 93, lab3 = 97, mid-term = 96, final exam = 94}
	 * Time estimate: O(n), n = number of students
	 */
	public void InputData(Vector<Student> studentList, double weighted[]) throws IOException {
		String cwd = System.getProperty("user.dir");
		FileInputStream fr = new FileInputStream(cwd+"/input.txt");
		Scanner inf = new Scanner(new InputStreamReader(fr,"UTF-8"));
		while (inf.hasNext()) {
			int id = inf.nextInt();
			String name = inf.next();
			int lab1 = inf.nextInt();
			int lab2 = inf.nextInt();
			int lab3 = inf.nextInt();
			int mid = inf.nextInt();
			int final_exam = inf.nextInt();
			Student newPerson = new Student(id, name, lab1, lab2, lab3, mid, final_exam);
			newPerson.calValue(weighted);
			studentList.add(newPerson);
		}
		fr.close();
	}
	
	
	/*
	 * check whether the id is in the data or not
	 * @param ID = input ID
	 * @param studentList = data
	 * @return vector position of the ID
	 * 
	 * Example: someObject.checkID(00000, data); return -1 for nonexistent.
	 * Time estimate: O(n)
	 */
	public static int checkID(double inputID, Vector<Student> studentList) {
		int number = -1;
		for(int i=0; i<studentList.size(); i++) {
			if(inputID == studentList.get(i).ID) {
				number = i;
			}
		}
		return number;
	}
	
	public void testCalculateTotalGrade() {
		
	}
}

class Student implements Comparable<Student> {
	public int ID;
	public String name;
	public int lab1, lab2, lab3, mid, final_exam;
	public double value;
	
	
	public Student(int ID_, String name_, int lab1_, int lab2_, int lab3_, int mid_, int final_exam_) {
		ID = ID_;
		name = name_;
		lab1 = lab1_;
		lab2 = lab2_;
		lab3 = lab3_;
		mid = mid_;
		final_exam = final_exam_;
	}
	public void showGrade() {
		System.out.println(name + "��蜀憒���" );
		System.out.println("Lab1 : "+ lab1 );
		System.out.println("Lab2 : "+ lab2);
		System.out.println("Lab3 : "+ lab3);
		System.out.println("Midterm : "+ mid );
		System.out.println("Final Exam : "+ final_exam );
	}
	public void calValue(double weight[]) {
		value = lab1*weight[0] + lab2*weight[1] + lab3*weight[2] +
				mid*weight[3] + final_exam*weight[4];
	}
	public void showValue() {
		System.out.println(name + "��像���蜀�嚗�" + value + "���" + "\n");
	}
	
	@Override
    public int compareTo(Student other) {
		if(value > other.value) {return 1;}
		else if(value < other.value) {return -1;}
		else {return 0;}
    }
	
}

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
		
		System.out.print("輸入ID或 Q (結束使用)?:\n");

		Scanner scanner = new Scanner(System.in);
		int position = A.Input(studentList, scanner); 		
		while (position == -1) { 			
			System.out.print("輸入ID或 Q (結束使用)?:\n"); 			
			position = A.Input(studentList, scanner);
			// Not exist 		} 		
			if (position == -2) return; //Q
		}
		
		String Continue = "YES";
		while(Continue.contentEquals("YES")) {
			System.out.print("輸入指令\n1) G顯示成績 \n2) R顯示排名 \n3) A顯示平均 \n4) W更新配分 \n5) E離開選單\n");
			String num1 = scanner.next();

			if(num1.contentEquals("G")) {
				studentList.get(position).showGrade();
			}else if(num1.contentEquals("R")) {
				Collections.sort(studentList);
				position = checkID(currentId, studentList);
				System.out.println(studentList.get(position).name + "的排名為 : " + position);
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
	
	public double[] changeWeight(double weighted[], Vector<Student> studentList, int position, Scanner scanner) {
		System.out.println("舊配分：lab1" + weighted[0]*100 + "%" + "lab2 " + weighted[1]*100 + "%" +
							"lab3 " + weighted[2]*100 + "%" + "midterm " + weighted[3]*100 + "%" + 
							"Final Exam " + weighted[4]*100 + "%" + "\n" + "請輸入配分:");
		
		
		return weighted;
	}
	
	public int Exit(main A, Vector<Student> studentList, Scanner scanner, int position) {
		System.out.print("輸入​ID​或​ Q (​結束使用​)?"); 
		int exitFlag = 0;
		position = A.Input(studentList, scanner); 				
		while (position == -1) 
			position = A.Input(studentList, scanner); // Not exist 				
		if (position == -2) { 
			exitFlag = 1;
		}
		return exitFlag;
	}
	
	public int Input(Vector<Student> studentList, Scanner scanner) {
		// Q or input ID
		String num1 = scanner.next();
		double num;
		if(num1.contentEquals("Q")) {
			System.out.print("結束了\n");
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
			System.out.print("無此人員!\n");
		}
		return position;
	}
	
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
	 * @param v1 = data
	 * @return number
	 * 
	 * Example: someObject.checkID(12345, data); return -1 for nonexistent.
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
		System.out.println(name + "成績如下：" + "\n");
		System.out.println("Lab1 : "+ lab1 +  "\n");
		System.out.println("Lab2 : "+ lab2+  "\n");
		System.out.println("Lab3 : "+ lab3 +  "\n");
		System.out.println("Midterm : "+ mid +  "\n");
		System.out.println("Final Exam : "+ final_exam +  "\n");
	}
	public void calValue(double weight[]) {
		value = lab1*weight[0] + lab2*weight[1] + lab3*weight[2] +
				mid*weight[3] + final_exam*weight[4];
	}
	public void showValue() {
		System.out.println(name + "的平均成績為：" + value + "分" + "\n");
	}
	
	@Override
    public int compareTo(Student other) {
		if(value > other.value) {return 1;}
		else if(value < other.value) {return -1;}
		else {return 0;}
    }
	
}

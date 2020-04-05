package demo;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class main {
	
	public static void main(String args[]) throws IOException{
		
		main A = new main();
		Vector<Student> studentList;
		A.InputData(studentList);
		
		System.out.print("輸入ID或 Q (結束使用)?:\n");

		Scanner scanner = new Scanner(System.in); 		
		int position = A.Input(data, name, scanner); 		
		while (position == -1) { 			
			System.out.print("輸入ID或 Q (結束使用)?:\n"); 			
			position = A.Input(data, name,scanner); 
			// Not exist 		} 		
			if (position == -2) return; //Q
		}
		
		String Continue = "YES";
		while(Continue.contentEquals("YES")) {
			System.out.print("輸入指令\n1) G顯示成績 \n2) R顯示排名 \n3) A顯示平均 \n4) W更新配分 \n5) E離開選單\n");
			String num1 = scanner.next();
			
			if(num1.contentEquals("G")) {
				System.out.println(name.get(position)+ "成績如下：" + "\n");
				System.out.println("Lab1 : "+ data.get(position).get(1) +  "\n");
				System.out.println("Lab2 : "+ data.get(position).get(2) +  "\n");
				System.out.println("Lab3 : "+ data.get(position).get(3) +  "\n");
				System.out.println("Midterm : "+ data.get(position).get(4) +  "\n");
				System.out.println("Final Exam : "+ data.get(position).get(5) +  "\n");
			}else if(num1.contentEquals("R")) {
				
			}else if(num1.contentEquals("A")) {
				System.out.println(name.get(position)+ "平均成績為" + data.get(position).get(6));
			}else if(num1.contentEquals("W")) {
			
			}else if(num1.contentEquals("E")) {
				System.out.print("輸入​ID​或​ Q (​結束使用​)?"); 				
				position = A.Input(data, name,scanner); 				
				while (position == -1) 
					position = A.Input(data, name,scanner); // Not exist 				
				if (position == -2) { 					
					return; // Q
				}
			}else {
				System.out.print("Wrong input!\n");
			}
		}
	}
	
	public int Input(Vector<Vector<Double>>data, Vector<String>name, Scanner scanner) {
		// Q or input ID
		String num1 = scanner.next();
		double num;
		if(num1.contentEquals("Q")) {
			System.out.print("結束了\n");
			return -2;
		}else {
			num = Double.parseDouble(num1);
		}
		
		int position = checkID(num,data);
		if(position != -1) {
			System.out.printf("Welcome, %s\n", name.get(position));
		}
		else {
			System.out.print("無此人員!\n");
		}
		return position;
	}
	
	public void InputData(Vector<Student> studentList) throws IOException {
		String cwd = System.getProperty("user.dir");
		FileInputStream fr = new FileInputStream(cwd+"/input.txt");
		Scanner inf = new Scanner(new InputStreamReader(fr,"UTF-8"));
		double weighted[] = {0.1,0.1,0.1,0.3,0.4};
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
	
	public double Weighted(Vector<Double> v2, double a, double b, double c, double d, double e) {
		//0.1, 0.1, 0.1, 0.3, 0.4 ​
		double value = v2.get(1)*a+ v2.get(2)*b + v2.get(3)*c + v2.get(4)*d + v2.get(5)*e;
		return value;
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
	
	public int checkID(double inputID, Vector<Vector<Double>> v1) {
		int number = -1;
		for(int i=0; i<v1.size(); i++) {
			if(inputID == v1.get(i).get(0)) {
				number = i;
			}
		}
		return number;
	}
	
	public void testCalculateTotalGrade() {
		
	}
}

class Student {
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
	
}

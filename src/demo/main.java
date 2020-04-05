package demo;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class main {
	
	public static void main(String args[]) throws IOException{
		Vector<Vector<Double>> data=new Vector<Vector<Double>>();
		Vector<String> name = new Vector<String>();
		
		main A = new main();
		A.InputData(data, name);
		
		System.out.print("輸入ID或 Q (結束使用)?:\n");

		int position = A.Input(A, data, name);
		while (position == -1) position = A.Input(A, data, name); // Not exist
		if (position == -2) return; //Q
		
		String Continue = "YES";
		while(Continue.contentEquals("YES")) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("輸入指令\n1) G顯示成績 \n2) R顯示排名 \n3) A顯示平均 \n4) W更新配分 \n5) E離開選單\n");
			String num1 = scanner.next();
			
			if(num1.contentEquals("G")) {
			
			}else if(num1.contentEquals("R")) {
			
			}else if(num1.contentEquals("A")) {
				System.out.println(name.get(position)+ "平均成績為" + data.get(position).get(6));
			}else if(num1.contentEquals("W")) {
			
			}else if(num1.contentEquals("E")) {
				System.out.print("輸入​ID​或​ Q (​結束使用​)?");
				position = A.Input(A, data, name);
				while (position == -1) position = A.Input(A, data, name); // Not exist
				if (position == -2) {
					scanner.close();
					return; // Q
				}
			}else {
				System.out.print("Wrong input!\n");
			}
			scanner.close();
		}
	}
	
	public int Input(main A, Vector<Vector<Double>>data, Vector<String>name) {
		Scanner scanner = new Scanner(System.in);
		// Q or input ID
		String num1 = scanner.next();
		double num;
		if(num1.contentEquals("Q")) {
			System.out.print("結束了\n");
			return -2;
		}else {
			num = Double.parseDouble(num1);
		}
		
		int position = A.checkID(num,data);
		if(position != -1) {
			System.out.printf("Welcome, %s\n", name.get(position));
		}
		else {
			System.out.print("無此人員!\n");
		}
		scanner.close();
		return position;
	}
	
	public void InputData(Vector<Vector<Double>> v1, Vector<String> name) throws IOException {

		String cwd = System.getProperty("user.dir");
		FileInputStream fr = new FileInputStream(cwd+"/input.txt");
		Scanner inf = new Scanner(new InputStreamReader(fr,"UTF-8"));
		
		while (inf.hasNext()) {
			Vector<Double> v2 = new Vector<Double>();
			v2.add(inf.nextDouble());
			name.add(inf.next());
			for (int i=0; i<5; i++) {
				v2.add(inf.nextDouble());
			}
			double weighted = Weighted(v2, 0.1, 0.1, 0.1, 0.3, 0.4);
			v2.add(weighted);
			v1.add(v2);
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

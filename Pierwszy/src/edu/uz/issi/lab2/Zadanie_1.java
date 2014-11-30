package edu.uz.issi.lab2;

import java.io.IOException;
import java.util.Scanner;

public class Zadanie_1 {
	public static void main(String[] args) throws IOException {

		Rectangle myRect = new Rectangle();
		myRect.width = 40;
		myRect.height = 50;
		System.out.println("Obszar kwadratu: " + myRect.area());
		
		Scanner wymiar = new Scanner(System.in);
		
		int x, y;
		
		try {
			System.out.print("Podaj wymiar X: ");
			x = wymiar.nextInt();
			System.out.print("Podaj wymiar Y: ");
			y = wymiar.nextInt();
			
			Point p1 = new Point();
			Point p2 = new Point(3);
			Point p3 = new Point(1,2);
			Point p4 = new Point(p3);
			Point p5 = new Point(x,y);
			
			System.out.println("Liczba obiektow = " + Point.objectCounter);
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p3);
			System.out.println(p4);
			System.out.println(p5);
			
			p2=null;
			System.gc();
			System.in.read();
			System.out.println("Liczba obiektow = " + Point.objectCounter);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wymiar.close();
		}
	}
}

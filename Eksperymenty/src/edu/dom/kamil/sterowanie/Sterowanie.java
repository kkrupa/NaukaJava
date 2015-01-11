package edu.dom.kamil.sterowanie;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Sterowanie {

	public static void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			System.out.println("UP");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("DOWN");
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("RIGHT");
			break;
		case KeyEvent.VK_ESCAPE:
			System.out.println("EXIT");
			break;
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
	}

}

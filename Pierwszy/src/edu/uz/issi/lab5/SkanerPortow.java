package edu.uz.issi.lab5;

import java.io.IOException;
import java.net.Socket;

class ThreadOfScanner extends Thread {
	int port;

	public ThreadOfScanner(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			Socket s = new Socket("localhost", port);
			System.out.println("Otwarty port: " + port);
			s.close();
		} catch (IOException e) {
			System.out.println("Zamkniety port: " + port);
		}
	}
}

public class SkanerPortow {
	
	public static int [] tablicaWynikow = new int[65536];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreadGroup grupa = new ThreadGroup("skanerki");
		
		for (int port = 1; port < 30000; port++) {
			new ThreadOfScanner(port).start();
		}
	}

}

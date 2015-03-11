package edu.uz.issi.lab5;

import java.io.*;
import java.net.*;

public class Reverse {
	static final String ADDRESS = "http://192.168.22.114:2222";

	public static void main(String[] args) throws Exception {
		if (args.length != 1) { // tylko jedno słowo
			System.err.println("Usage: java Reverse string_to_reverse");
			System.exit(1);
		}
		
		String stringToReverse = URLEncoder.encode(args[0], "UTF-8"); // kodowanie
		
		URL url = new URL(ADDRESS);
		URLConnection connection = url.openConnection();
		
		connection.setDoOutput(true); // ustawienie w tryb zapisu
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream()); // pobranie strumienia
												// out.write("string=" +
												// stringToReverse); //zapis
		
		out.close(); // zamknięcie strumienia wyjściowego do zasobu
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String decodedString;
		while ((decodedString = in.readLine()) != null) {
			System.out.println(decodedString);
		} // odczytanie ze strumienia wejściowego in.close();
	}
}
package edu.uz.issi.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class URLConnect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL myURL = new URL("http://192.168.22.114:2222");
			URLConnection urlConnection = myURL.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			String inputLine;
			while((inputLine = in.readLine()) != null) System.out.println(inputLine);
			
			in.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

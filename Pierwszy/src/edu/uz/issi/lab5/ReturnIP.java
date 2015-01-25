package edu.uz.issi.lab5;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReturnIP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress address = null;
		try {
			//address = InetAddress.getByName(args[0]);
			//System.out.println("Nazwa hosta: " + address.getHostName());
			//System.out.println("Adres IP: " + address.getHostAddress());
			
			InetAddress [] addresses = InetAddress.getAllByName(args[0]);
			
			for(InetAddress i:addresses) System.out.println(i);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}

/**
 * 
 */
package edu.uz.issi.lab1.demo;

/**
 * To jest moja pierwsza klasa
 * 
 * @author Kamil Krupa
 *
 */
public class HelloWorld {

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {

		if (args != null && args.length >= 2)
			System.out.print("Hello " + args[0] + " " + args[1] + "!");
		else
			System.err.print("Podaj 2 argumenty.");

	}

}

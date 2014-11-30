package edu.dom.kamil.selfstudy;

public class Eksperymenty2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] tablica = {45, 22, 12, 56, 0, 52};
		System.out.println(average(tablica));
	}

	/**
	 * @param numbers
	 * @return
	 */
	public static float average (int...numbers) {
		float total = 0;
		for(int x: numbers) {
			total += x;
		}
		
		return total/numbers.length;
	}
	
}

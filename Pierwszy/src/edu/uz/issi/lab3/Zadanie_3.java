package edu.uz.issi.lab3;

public class Zadanie_3 {
	protected InnerClass1 ic;

	public Zadanie_3() {
		ic = new InnerClass1();
	}

	public void displayStrings() {
		System.out.println(ic.getString() + ".");
		System.out.println(ic.getAnotherString() + ".");
	}

	static public void main(String[] args) {
		Zadanie_3 c1 = new Zadanie_3();
		c1.displayStrings();
	}

	protected class InnerClass1 {
		public String getString() {
			return "InnerClass1: getString wywołane";
		}

		public String getAnotherString() {
			return "InnerClass1: getAnotherString wywołane";
		}
	}
}
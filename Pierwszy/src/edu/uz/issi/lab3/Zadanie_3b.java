package edu.uz.issi.lab3;

public class Zadanie_3b extends Zadanie_3 {

	public Zadanie_3b() {
		ic = new InnerClass2();
	}

	public void displayStrings() {
		System.out.println(ic.getString() + ".");
		System.out.println(ic.getAnotherString() + ".");
	}

	static public void main(String[] args) {
		Zadanie_3 c1 = new Zadanie_3b();
		c1.displayStrings();
	}

	protected class InnerClass2 extends InnerClass1 {
		public String getString() {
			return "InnerClass2: getString wywołane";
		}

		public String getAnotherString() {
			return "InnerClass2: getAnotherString wywołane";
		}
	}
}
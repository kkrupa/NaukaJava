package edu.uz.issi.lab4;

class ProdKonsTest {
	public static void main(String[] args) throws Exception {
		Pudelko c = new Pudelko();
		Producent p1 = new Producent(c, 1);
		Konsument c1 = new Konsument(c, 1);
		Producent p2 = new Producent(c, 2);
		Konsument c2 = new Konsument(c, 2);
		p1.start();
		c1.start();
		p2.start();
		//c2.start();
	}

	static void pauza() throws java.io.IOException {
		System.out.println("Nacisnij Enter...");
		System.in.read();
	}
}
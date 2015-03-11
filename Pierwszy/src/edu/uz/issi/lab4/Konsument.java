package edu.uz.issi.lab4;

class Konsument extends Thread {
	private Pudelko pudelko;
	private int m_nLiczba;

	public Konsument(Pudelko c, int Liczba) {
		pudelko = c;
		this.m_nLiczba = Liczba;
	}

	public void run() {
		int wartosc = 0;
		for (int i = 0; i < 10; i++) {
			wartosc = pudelko.wez(m_nLiczba);
			//System.out.println("Konsument #" + this.m_nLiczba + " wyjal: " + wartosc);
		}
	}
}
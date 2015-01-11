package edu.uz.issi.lab4;

class Pudelko {
	private int m_nZawartosc; // zmienna warunkowa,
	private boolean m_bDostepne = false;

	public synchronized int wez(int idProducenta) {
		while (m_bDostepne == false) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		m_bDostepne = false;
		notifyAll(); // zawiadomienie Producenta
		System.out.println("Konsument #" + idProducenta + " wyjal: " + m_nZawartosc);
		return m_nZawartosc;
	}

	public synchronized void wloz(int wartosc, int idProducenta) {
		while (m_bDostepne == true) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		m_nZawartosc = wartosc;
		m_bDostepne = true;
		notifyAll(); // zawiadomienie Konsumenta
		System.out.println("Producent #" + idProducenta + " wlozyl: " + wartosc);
	}
}

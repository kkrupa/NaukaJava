package edu.uz.issi.lab4;

public class SelfishRunner extends Thread {
	private int tick = 1;
	private int num;

	public SelfishRunner(int numer_watku) {
		this.num = numer_watku;
	}

	public void run() {
		while (tick < 400000) {
			tick++;

			if ((tick % 50000) == 0)
				System.out.println("Wątek #" + num + ", czas = " + tick);
		}
	}
}

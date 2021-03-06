package edu.dom.kamil.selfstudy;

public class Czas {
	
	private int hour;
	private int minutes;
	private int seconds;
	
	public Czas () {
		hour = 0;
		minutes = 0;
		seconds = 0;
	}
	
	public void setTime(int h, int m, int s) {
		hour = ((h>=0 && h <24) ? h : 0);
		minutes = ((m>=0 && m<60) ? m : 0);
		seconds = ((s>=0 && s<60) ? s : 0);
	}

	@Override
	public String toString() {
		// returns time in hh:mm:ss format
		return String.format("%02d:%02d:%02d", hour, minutes, seconds);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	
}

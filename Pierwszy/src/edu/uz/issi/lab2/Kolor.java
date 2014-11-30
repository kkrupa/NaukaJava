package edu.uz.issi.lab2;

import java.awt.Color;

public class Kolor {
	Color color;
	
	public Kolor(int r, int g, int b) {
		color = new Color(r,g,b);
	}

	public Kolor(Color color) {
		this.color = color;
	}
	
	public Kolor() {
		this(0,0,0);
	}
	
}

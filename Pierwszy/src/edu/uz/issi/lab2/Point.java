package edu.uz.issi.lab2;

public class Point extends Kolor {
	public int x = 0;
	public int y = 0;
	static int objectCounter=0;

	{
		objectCounter++;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		this(0,0);
	}

	public Point(int x) {
		this(x,0);
	}

	public Point(Point p) {
		this(p.x,p.y);
	}

	@Override
	public String toString() {
		return "Point ("+x+", "+y+")";
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		objectCounter--;
	}
}

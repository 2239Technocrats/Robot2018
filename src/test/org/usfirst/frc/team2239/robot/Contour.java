package org.usfirst.frc.team2239.robot;

public class Contour {
	//All these parameters will be visible to classes within the same package as this class (see "package" above)
	final double notFoundDefault = 0;
	double x;
	double y;
	double w;
	double h;
	double area;
	
	public Contour() {
		this.x = notFoundDefault;
		this.y = notFoundDefault;
		this.w = notFoundDefault;
		this.h = notFoundDefault;
		this.area = notFoundDefault;
	}
	
	public String toString() {
		return "Countour at ("+x+", "+y+") with width: "+w+" height: "+h+" area: "+area;
	}
}

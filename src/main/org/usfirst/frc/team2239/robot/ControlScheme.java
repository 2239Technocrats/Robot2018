package main.org.usfirst.frc.team2239.robot;

import java.util.*;
import edu.wpi.first.wpilibj.drive.*;

public class ControlScheme {
	public char[][] triggerTypes = new char[20][3];
	private final char speed = 'a';					//a
	private final char shift = 'b';					//b
	private final char grabber = 'c';				//c
	private final char liftRaise = 'd';				//d
	private final char liftLower = 'e';				//e
	private final char grabberSucc = 'f';			//f
	private final char grabberBlow = 'g';			//g
	private final char CSVSave = 'h';				//h
	private final char toggleX = 'i';				//i
	private final char toggleXX = 'j';				//j
	private final char toggleXJ = 'k';				//k
	private final char toggleJ = 'l';				//l
	private final char toggleJJ = 'm';				//m
	private final char update = 'n';				//n
	//Tank Drive
	private final char leftSide = 'o';				//o
	private final char rightSide = 'p';				//p
	//Arcade Drive
	private final char forwards = 'q';				//q
	private final char rotation = 'r';				//r
	
	private final char none = 's';					//s  (placeholder)
	
	public ControlScheme(String name) {
		init(name);
		
	}
	
	private void init(String name) {
		if(name == "x") {
			// buttons
			char[][] newTriggerTypes = {
					{speed, 'b', (char)1},
					{shift, 'b', (char)2},
					{liftLower, 'b', (char)3},
					{liftRaise, 'b', (char)4},
					{grabber, 'b', (char)5},
					{grabber, 'b', (char)6},
					{update, 'b', (char)7},
					{update, 'b', (char)8},
					{none, 'b', (char)9},
					{none, 'b', (char)10},
					{none, 'a', (char)1},
					{leftSide, 'a', (char)2},
					{grabberSucc, 'a', (char)3},
					{grabberBlow, 'a', (char)4},
					{none, 'a', (char)5},
					{rightSide, 'a', (char)6},
					{toggleX, 'p', (char)1},
					{toggleXX, 'p', (char)2},
					{toggleXJ, 'p', (char)3},
					{toggleJ, 'p', (char)4},
					{toggleJJ, 'p', (char)5},
					{CSVSave, 'p', (char)6},
					{none, 'p', (char)7},
					{none, 'p', (char)8}
			};
			triggerTypes = newTriggerTypes;
			
			
			// POV
			triggerTypes[0][0] = 'i';
			triggerTypes[0][1] = 'o';
			triggerTypes[0][2] = (char)1;
			triggerTypes[0][0] = 'j';
			triggerTypes[0][1] = 'o';
			triggerTypes[0][2] = (char)2;
			triggerTypes[0][0] = 'k';
			triggerTypes[0][1] = 'o';
			triggerTypes[0][2] = (char)3;
			triggerTypes[0][0] = 'l';
			triggerTypes[0][1] = 'o';
			triggerTypes[0][2] = (char)4;
			triggerTypes[0][0] = 'm';
			triggerTypes[0][1] = 'o';
			triggerTypes[0][2] = (char)5;
			triggerTypes[0][0] = 'h';
			triggerTypes[0][1] = 'c';
			triggerTypes[0][2] = (char)6;
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'p';
			triggerTypes[0][2] = (char)7;
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'p';
			triggerTypes[0][2] = (char)8;
		}
		else if(name == "xx"){
			
		}
		else if(name == "jj"){
			
		}
		else if(name == "xj"){
			
		}
		else {
			System.out.println("Unknown controlScheme name: " + name);
		}
	}
	
	public void setActive(String controlScheme){
		init(controlScheme);
	}
	
	
}

package main.org.usfirst.frc.team2239.robot;

import java.util.*;
import edu.wpi.first.wpilibj.drive.*;

public class ControlScheme {
	public char[][] triggerTypes = new char[20][3];
	private final char speed = 'a';							//a
	private final char shift = 'b';							//b
	private final char grabber = 'c';						//c
	private final char liftRaise = 'd';						//d
	private final char liftLower = 'e';						//e
	private final char grabberSucc = 'f';					//f
	private final char grabberBlow = 'g';					//g
	private final char CSVSave = 'h';						//h
	private final char toggleX = 'i';						//i
	private final char toggleXX = 'j';						//j
	private final char toggleXJ = 'k';						//k
	private final char toggleJ = 'l';						//l
	private final char toggleJJ = 'm';						//m
	private final char update = 'n';						//n
	
	//Tank Drive
	private final char leftSide = 'o';						//o
	private final char rightSide = 'p';						//p
	//Arcade Drive
	private final char forwards = 'q';						//q
	private final char rotation = 'r';						//r
	
	private final char none = 's';							//s  (placeholder)
	
	
	
	public HashMap<String, Integer> active;
	public RobotDriveBase driveTrain;
	
	public ControlScheme(String name, RobotDriveBase driveTrain) {
		init(name);
		this.driveTrain = driveTrain;
	}
	
	private void init(String name){
		if(name == "x"){
			
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
					{none, 'a', (char)0},
					{leftSide, 'a', (char)1},
					{grabberSucc, 'a', (char)2},
					{grabberBlow, 'a', (char)3},
					{none, 'a', (char)4},
					{rightSide, 'a', (char)5},
			};
			triggerTypes = newTriggerTypes;
			
			// axis
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'a';
			triggerTypes[0][2] = (char)1;
			triggerTypes[0][0] = 'o';
			triggerTypes[0][1] = 'a';
			triggerTypes[0][2] = (char)2;
			triggerTypes[0][0] = 'f';
			triggerTypes[0][1] = 'a';
			triggerTypes[0][2] = (char)3;
			triggerTypes[0][0] = 'g';
			triggerTypes[0][1] = 'a';
			triggerTypes[0][2] = (char)4;
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'a';
			triggerTypes[0][2] = (char)5;
			triggerTypes[0][0] = 'p';
			triggerTypes[0][1] = 'a';
			triggerTypes[0][2] = (char)6;
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
		
//		else if(name == "j"){
//			active = new HashMap<String, Integer>();
//			// buttons
//			active.put(GB, 1);
//			active.put(SP, 2);
//			active.put(GT, 3);
//			active.put(LL, 4);
//			active.put(GS, 5);
//			active.put(LR, 6);
//			active.put(TCX, 7);
//			active.put(TCXX, 8);
//			active.put(TCXJ, 9);
//			active.put(TCJ, 10);
//			active.put(TCJJ, 11);
//			active.put(UPDATE, 12);
//
//			// axis
//			active.put(FW, 0);
//			active.put(ROT, 1);
//			active.put("NONE", 2);
//			active.put(SP, 3);
//
//			// POV
//			active.put("NONE", 1);
//			active.put("NONE", 2);
//			active.put("NONE", 3);
//			active.put("NONE", 4);
//			active.put("NONE", 5);
//			active.put("NONE", 6);
//			active.put("NONE", 7);
//			active.put("NONE", 8);				
//		}
		
		else if(name == "xx"){
			
		}
		
		else if(name == "jj"){
			
		}
		
		else if(name == "xj"){
			
		}
		
		else{
			System.out.println("@init controlScheme formatted wrong");
		}
			
	}
	
	public void setDriveTrain(RobotDriveBase driveTrain){
		this.driveTrain = driveTrain;
	}
	public void setActive(String controlScheme){
		init(controlScheme);
	}
	
	
}

package main.org.usfirst.frc.team2239.robot;

import java.util.*;
import edu.wpi.first.wpilibj.drive.*;

public class ControlScheme {
	public char[][] triggerTypes = new char[19][2];
	private final String SP = "SPEED";							//a
	private final String SH = "SHIFT";							//b
	private final String GT = "GRABBER_TOGGLE";					//c
	private final String LR = "LIFT_RAISE";						//d
	private final String LL = "LIFT_LOWER";						//e
	private final String GS = "GRABBER_SUCC";					//f
	private final String GB = "GRABBER_BLOW";					//g
	private final String SS = "SPREADSHEET_SAVE";				//h
	private final String TCX = "TOGGLE_CONTROLLER";				//i
	private final String TCXX = "TOGGLE_CONTROLLER";			//j
	private final String TCXJ = "TOGGLE_CONTROLLER";			//k
	private final String TCJ = "TOGGLE_CONTROLLER";				//l
	private final String TCJJ = "TOGGLE_CONTROLLER";			//m
	private final String UPDATE = "UPDATE_ACTIVE_CONTROLLERS";	//n
	
	//Tank Drive
	private final String LS = "LEFT_SIDE";						//o
	private final String RS = "RIGHT_SIDE";						//p
	//Arcade Drive
	private final String FW = "FORWARDS";						//q
	private final String ROT = "ROTATION";						//r
	
	private final String NONE = "NONE";							//s  (placeholder)
	private final String NOTHING = "NONE";						//t  (placeholder)
	
	
	public HashMap<String, Integer> active;
	public RobotDriveBase driveTrain;
	
	public ControlScheme(String name, RobotDriveBase driveTrain) {
		init(name);
		this.driveTrain = driveTrain;
	}
	
	private void init(String name){
		if(name == "x"){
			active = new HashMap<String, Integer>();
			// buttons
			active.put(SP, 1);
			triggerTypes[0][0] = 'a';
			triggerTypes[0][1] = 'b';
			active.put(SH, 2);
			triggerTypes[0][0] = 'b';
			triggerTypes[0][1] = 'b';
			active.put(LL, 3);
			triggerTypes[0][0] = 'e';
			triggerTypes[0][1] = 'b';
			active.put(LR, 4);
			triggerTypes[0][0] = 'd';
			triggerTypes[0][1] = 'b';
			active.put(GT, 5);
			triggerTypes[0][0] = 'c';
			triggerTypes[0][1] = 'b';
			active.put(GT, 6);
			triggerTypes[0][0] = 'c';
			triggerTypes[0][1] = 'b';
			active.put(UPDATE, 7);
			triggerTypes[0][0] = 'n';
			triggerTypes[0][1] = 'b';
			active.put(UPDATE, 8);
			triggerTypes[0][0] = 'n';
			triggerTypes[0][1] = 'b';
			active.put("NONE", 9);
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'b';
			active.put("NONE", 10);
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'b';
			// axis
			active.put("NONE", 0);
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'a';
			active.put(LS, 1);
			triggerTypes[0][0] = 'o';
			triggerTypes[0][1] = 'a';
			active.put(GS, 2);
			triggerTypes[0][0] = 'f';
			triggerTypes[0][1] = 'a';
			active.put(GB, 3);
			triggerTypes[0][0] = 'g';
			triggerTypes[0][1] = 'a';
			active.put("NONE", 4);
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'a';
			active.put(RS, 5);
			triggerTypes[0][0] = 'p';
			triggerTypes[0][1] = 'a';
			// POV
			active.put(TCX, 1);
			triggerTypes[0][0] = 'i';
			triggerTypes[0][1] = 'p';
			active.put(TCXX, 2);
			triggerTypes[0][0] = 'j';
			triggerTypes[0][1] = 'p';
			active.put(TCXJ, 3);
			triggerTypes[0][0] = 'k';
			triggerTypes[0][1] = 'p';
			active.put(TCJ, 4);
			triggerTypes[0][0] = 'l';
			triggerTypes[0][1] = 'p';
			active.put(TCJJ, 5);
			triggerTypes[0][0] = 'm';
			triggerTypes[0][1] = 'p';
			active.put(SS, 6);
			triggerTypes[0][0] = 'h';
			triggerTypes[0][1] = 'p';
			active.put("NONE", 7);
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'p';
			active.put("NONE", 8);
			triggerTypes[0][0] = 's';
			triggerTypes[0][1] = 'p';
		}
		
		else if(name == "j"){
			active = new HashMap<String, Integer>();
			// buttons
			active.put(GB, 1);
			active.put(SP, 2);
			active.put(GT, 3);
			active.put(LL, 4);
			active.put(GS, 5);
			active.put(LR, 6);
			active.put(TCX, 7);
			active.put(TCXX, 8);
			active.put(TCXJ, 9);
			active.put(TCJ, 10);
			active.put(TCJJ, 11);
			active.put(UPDATE, 12);

			// axis
			active.put(FW, 0);
			active.put(ROT, 1);
			active.put("NONE", 2);
			active.put(SP, 3);

			// POV
			active.put("NONE", 1);
			active.put("NONE", 2);
			active.put("NONE", 3);
			active.put("NONE", 4);
			active.put("NONE", 5);
			active.put("NONE", 6);
			active.put("NONE", 7);
			active.put("NONE", 8);				
		}
		
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

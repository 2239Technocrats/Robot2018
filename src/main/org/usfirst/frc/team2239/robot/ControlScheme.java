package main.org.usfirst.frc.team2239.robot;

import java.util.*;
import edu.wpi.first.wpilibj.drive.*;

public class ControlScheme {
	public char[] triggerTypes = new char[18];
	private final String SP = "SPEED";
	private final String SH = "SHIFT";
	private final String GT = "GRABBER_TOGGLE";
	private final String LR = "LIFT_RAISE";
	private final String LL = "LIFT_LOWER";
	private final String GS = "GRABBER_SUCC";
	private final String GB = "GRABBER_BLOW";
	private final String SS = "SPREADSHEET_SAVE";
	private final String TCX = "TOGGLE_CONTROLLER";
	private final String TCXX = "TOGGLE_CONTROLLER";
	private final String TCXJ = "TOGGLE_CONTROLLER";
	private final String TCJ = "TOGGLE_CONTROLLER";
	private final String TCJJ = "TOGGLE_CONTROLLER";
	private final String UPDATE = "UPDATE_ACTIVE_CONTROLLERS";
	
	//Tank Drive
	private final String LS = "LEFT_SIDE";
	private final String RS = "RIGHT_SIDE";
	//Arcade Drive
	private final String FW = "FORWARDS";
	private final String ROT = "ROTATION";
	
	
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
			active.put(SH, 2);
			active.put(LL, 3);
			active.put(LR, 4);
			active.put(GT, 5);
			active.put(GT, 6);
			active.put(UPDATE, 7);
			active.put(UPDATE, 8);
			active.put("NONE", 9);
			active.put("NONE", 10);
			// axis
			active.put("NONE", 0);
			active.put(LS, 1);
			active.put(GS, 2);
			active.put(GB, 3);
			active.put("NONE", 4);
			active.put(RS, 5);
			// POV
			active.put(TCX, 1);
			active.put(TCXX, 2);
			active.put(TCXJ, 3);
			active.put(TCJ, 4);
			active.put(TCJJ, 5);
			active.put(SS, 6);
			active.put("NONE", 7);
			active.put("NONE", 8);
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

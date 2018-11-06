package org.usfirst.frc.team2239.robot;

import java.util.*;

public class ControlScheme {
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
	
	//Tank Drive
	private final String LS = "LEFT_SIDE";
	private final String RS = "RIGHT_SIDE";
	//Arcade Drive
	private final String FW = "FORWARDS";
	private final String ROT = "ROTATION";
	
	public HashMap<String, Integer> active;
	private HashMap<String, Integer> x = new HashMap<String, Integer>();
	private HashMap<String, Integer> xx = new HashMap<String, Integer>();
	private HashMap<String, Integer> xj = new HashMap<String, Integer>();
	private HashMap<String, Integer> j = new HashMap<String, Integer>();
	private HashMap<String, Integer> jj = new HashMap<String, Integer>();
	
	public ControlScheme(String controlScheme) {
		init();
		setActive(controlScheme);
	}
	
	public void init(){
		// buttons
		x.put(SP, 1);
		x.put(SH, 2);
		x.put(LL, 3);
		x.put(LR, 4);
		x.put(GT, 5);
		x.put(GT, 6);
		x.put("NONE1", 7);
		x.put("NONE2", 8);
		x.put("NONE3", 9);
		x.put("NONE4", 10);
		// axis
		x.put("NOTE5", 0);
		x.put(LS, 1);
		x.put(GS, 2);
		x.put(GB, 3);
		x.put("NONE6", 4);
		x.put(RS, 5);
		// POV
		x.put(TCX, 1);
		x.put(TCXX, 2);
		x.put(TCXJ, 3);
		x.put(TCJ, 4);
		x.put(TCJJ, 5);
		x.put("ss", 6);
		x.put("NONE7", 7);
		x.put("NONE8", 8);
		
		// buttons
		j.put(GB, 1);
		j.put(SP, 2);
		j.put(GT, 3);
		j.put(LL, 4);
		j.put(GS, 5);
		j.put(LR, 6);
		j.put(TCX, 7);
		j.put(TCXX, 8);
		j.put(TCXJ, 9);
		j.put(TCJ, 10);
		j.put("TCJJ", 11);
		j.put("NONE", 12);

		// axis
		j.put(FW, 0);
		j.put("NONE", 1);
		j.put(ROT, 2);
		j.put(SP, 3);

		// POV
		j.put("NONE", 1);
		j.put("NONE", 2);
		j.put("NONE", 3);
		j.put("NONE", 4);
		j.put("NONE", 5);
		j.put("NONE", 6);
		j.put("NONE", 7);
		j.put("NONE", 8);	
		
		
	}
	
	public void setActive(String controlScheme){
		if(controlScheme == "x"){
			active = x;
		}else if(controlScheme == "xx"){
			
		}else if(controlScheme == "xj"){
			
		}else if(controlScheme == "j"){
			
		}else if(controlScheme == "jj"){
			
		}else{
			System.out.println("@setActive controlScheme formatted wrong");
		}
	}
	
	
}

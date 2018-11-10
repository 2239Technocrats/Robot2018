package org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2239.robot.XboxController;
import java.util.*;
import edu.wpi.first.wpilibj.DriverStation;



public class TechnoUI{
	
	DriverStation dsi;
	private int scheme = -1;
	String activeControllers;
	XboxController x;
	XboxController x2;
	Joystick j;
	Joystick j2;
	public TechnoUI(){
		updateControllers();
	}
	public void updateControllers(){
		activeControllers = "";
		if(dsi.getInstance().getJoystickName(0) == "Controller (Xbox 360 Pro Ex"){
			activeControllers +='x';
		}else if(dsi.getJoystickName(0) == ""){
			activeControllers +='j';
		}
	}
	public boolean setScheme(String setTo) {
		if (setTo == "deFault") {
			scheme = 1;
			return true;
		}
		System.out.println("Unknown scheme selected: " + setTo);
		return false;
	}
}

package main.org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.*;
import java.util.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;


public class TechnoUI {
	
	DriverStation dsi;
	private int scheme = -1;
	String activeControllers;
	XboxController x;
	XboxController x2;
	Joystick j;
	Joystick j2;
	ControlScheme activeCS;
	public TechnoUI(String name, RobotDriveBase driveTrain) {
		activeCS = new ControlScheme(name, driveTrain);
		updateControllers();
		activeCS.setActive(activeControllers);
		
	}
	public void updateControllers(){
		activeControllers = "";
		if(dsi.getJoystickName(0) == "Controller (Xbox 360 Pro Ex)") {
			activeControllers += 'x';
		}
		if(dsi.getJoystickName(1) == "Controller (Xbox 360 Pro Ex)") {
			activeControllers += 'x';
		}
		if(dsi.getJoystickName(2) == "Logitech Extreme 3D") {
			activeControllers += 'j';
		}
		if(dsi.getJoystickName(3) == "Logitech Extreme 3D") {
			activeControllers += 'j';
		}
		
	}
	public void updateScheme(){
		updateControllers();
		setScheme(activeControllers);
	}
	public void setScheme(String setTo) {
		if (setTo == "x"|| setTo == "xx" || setTo == "xj" || setTo == "j" || setTo == "jj"){
			activeCS.setActive(setTo);
		}
	}
	public void setDrive(RobotDriveBase driveTrain){
		this.activeCS.setDriveTrain(driveTrain);
	}
	public void controlRun(){
		
	}
}

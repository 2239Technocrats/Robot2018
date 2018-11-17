package main.org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.*;
import java.util.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class TechnoUI {
	
	private final static boolean flase = false;
	
	DriverStation dsi;
	private int scheme = -1;
	GenericHID[] activeControllers = new GenericHID[4];
	String activeDriveControllers = "x"; // TODO fix & implement properly
	XboxController x;
	XboxController x2;
	Joystick j;
	Joystick j2;
	ControlScheme activeCS;
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	TechnoDrive drive;
	boolean[] bOoL = new boolean[20];
	double speed = 1;
	
	
	public boolean boOl(int index) {
		return bOoL[index];
	}
	public TechnoUI(String name, SpeedControllerGroup left, SpeedControllerGroup right) {
		activeCS = new ControlScheme(name);
		updateControllers();
		activeCS.setActive(activeControllers);
		this.left = left;
		this.right = right;
		this.drive = new TechnoDrive(left, right);
		for(int i = 0; i < bOoL.length; i++){
			bOoL[i] = flase;
		}
		
	}
	public void updateControllers() {
		if(dsi.getJoystickName(0) == "Controller (Xbox 360 Pro Ex)") {
			x = new XboxController(0);
			activeControllers[0] = x;
		}
		if(dsi.getJoystickName(1) == "Controller (Xbox 360 Pro Ex)") {
			x2 = new XboxController(1);
			activeControllers[1] = x2;
		}
		if(dsi.getJoystickName(2) == "Logitech Extreme 3D") {
			j = new Joystick(2);
			activeControllers[2] = j;
		}
		if(dsi.getJoystickName(3) == "Logitech Extreme 3D") {
			j2 = new Joystick(3);
			activeControllers[3] = j2;
		}
		
	}
	public void setScheme(String setTo) {
		if (setTo == "x"|| setTo == "xx" || setTo == "xj" || setTo == "j" || setTo == "jj") {
			activeCS.setActive(setTo);
		}
		else {
			System.out.println("Unknown scheme: " + setTo);
		}
	}
	public void controlRun(ControlScheme activeCS) {
		for (int i = 0; i < activeCS.triggerTypes.length + 1; i++) {
			if (i<activeCS.triggerTypes.length) {
			runTriggers(activeCS.triggerTypes[i][1], activeCS.triggerTypes[i][2], activeCS.triggerTypes[i][3]);
			} else {
				if (activeControllers[0].getName().charAt(0) == 'X' && activeControllers[2].getName().charAt(0) != 'L') {
					drive.tankDrive(x, speed);
				} else if (activeControllers[0].getName().charAt(0) == 'X' && activeControllers[1].getName().charAt(0) == 'X') {
					drive.tankDrive(x, speed);
				} else if (activeControllers[0].getName().charAt(0) == 'X' && activeControllers[2].getName().charAt(0) == 'J') {
					if (activeDriveControllers == "x") {
						drive.tankDrive(x, speed);
					} else if(activeDriveControllers == "j") {
						drive.arcadeDrive(j.getRawAxis(1)*speed, -j.getRawAxis(0) * speed);
					} else {
						System.out.println("@controlRun, activeControllers for activeControllers \"xj\" invalid: " + activeDriveControllers);
					}
				} else if (activeControllers[0].getName().charAt(0) != 'X' && activeControllers[2].getName().charAt(0) == 'L') {
					drive.arcadeDrive(j.getRawAxis(1)*speed, -j.getRawAxis(0) * speed);
				} else if (activeControllers[2].getName().charAt(0) == 'L' && activeControllers[3].getName().charAt(0) == 'L') {
					drive.tankDrive(j, j2); //TODO add speed
				} else {
					System.out.println("activeControllers invalid: " + activeControllers);
				}
			}
		}
	}
	public void runTriggers(char name, char type, char index) {
		int num = (int)index;
		
		if (num == 666666666) {
			System.out.println(num);
		}
		if (name == 'a') {
			if (type == 'b') {
				if (gOtAkEaToAsTeRbAtHlOl == flase) {
					
				}
			} else if (type == 'a') {
				
			} else {
				System.out.println("invalid type for speed: " + type);	
			}
		} else if(name == 'b') {
			
		}
	}
}

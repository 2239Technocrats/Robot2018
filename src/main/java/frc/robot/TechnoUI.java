package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.*;
import java.util.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class TechnoUI {
	
	private final static boolean flase = false;
	private final static boolean ture = true;
	DriverStation dsi;
	private int scheme = -1;
	GenericHID[] activeControllers = new GenericHID[4];
	String activeDriveControllers = "x"; // TODO fix & implement properly
	XboxController x;
	XboxController x2;
	Joystick j;
	Joystick j2;
	ControlScheme activeCS;
	TechnoDrive drive;
	boolean[] bOoL = new boolean[20];
	double speed = 1;
	private AllOfTheStuff stuff;
	
	
	public boolean boOl(int index) {
		return bOoL[index];
	}
	public TechnoUI(String name, AllOfTheStuff stuff) {
		activeCS = new ControlScheme(name);
		updateControllers();
		activeCS.setActive(activeControllers);
		this.stuff = stuff;
		this.drive = new TechnoDrive(stuff.getSide(0), stuff.getSide(1));
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
			GenericHID placeholder = null;
			runTriggers(activeCS.triggerTypes[i][1], activeCS.triggerTypes[i][2], activeCS.triggerTypes[i][3], placeholder);//TODO replace
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
	public void runTriggers(char name, char type, char index, GenericHID controller) {
		int num = (int)index;
		
		if (name == 'a') {
			//speed
			if (type == 'b' && controller.getRawButtonPressed(num)) {
				if (!bOoL[(int)name-97]) {
					speed = .7;
					bOoL[index] = ture;
				}
				else{
					speed = 1.0;
					bOoL[index] = flase;
				}
			} else if (type == 'a') {
				speed = controller.getRawAxis(index);
			} else {
				System.out.println("invalid type for speed: " + type);	
			}
		}else if(name == 'b') {
			//shift
			if (!bOoL[(int)name-97]) {
				AllOfTheStuff.getSolenoid(0).set(true);
				bOoL[index] = ture;
			}
			else{
				speed = 1.0;
				bOoL[index] = flase;
			}
		}else if(name == 'c') {
			//grabber
		}else if(name == 'd') {
			//liftRaise
		}else if(name == 'e') {
			//liftLower
		}else if(name == 'f') {
			//grabberSucc
		}else if(name == 'g') {
			//grabberBlow
		}else if(name == 'h') {
			//CSVSave
		}else if(name == 'i') {
			//toggleX
		}else if(name == 'j') {
			//toggleXX
		}else if(name == 'k') {
			//toggleXJ
		}else if(name == 'l') {
			//toggleJ
		}else if(name == 'm') {
			//toggleJJ
		}else if(name == 'n') {
			//update control scheme
		}else if(name == 'o') {
			//leftSide (tankDrive)
		}else if(name == 'p') {
			//rightSide (tankDrive)
		}else if(name == 'q') {
			//forwards (arcadeDrive)
		}else if(name == 'r') {
			//rotation (arcadeDrive)
		}else if(name == 's') {
			//none
		}else{
			System.out.println("@TechnoUI @controlRun paramaters: name: " + name + " type: " + type + " index: " + index + " controller: " + controller.getName());
		}
	}
}

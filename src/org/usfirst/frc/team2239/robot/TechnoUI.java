package org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2239.robot.XboxController;
import java.util.*;
import edu.wpi.first.wpilibj.DriverStation;

public class TechnoUI{
	public static void testfunC() {
		HashMap<String, Integer> test = new HashMap<String, Integer>();
		test.put("Speed", 4);
		test.put("Graber", 7);
		Set set = test.entrySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			Map.Entry mentry = (Map.Entry)iter.next();
			System.out.println(mentry.getValue());
		}
	}
	DriverStation dsi;
	private int scheme = -1;
	String activeController;
	XboxController x;
	XboxController x2;
	Joystick j;
	Joystick j2;
	public TechnoUI(){
		updateControllers();
	}
	public void updateControllers(){
		activeController = "";
		if(dsi.getInstance().getJoystickIsXbox(0)){
			activeController +='x';
		}else if(dsi.get){
			activeController +='j';
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

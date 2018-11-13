package org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidAccelerator implements Action {
	private Timer timer = new Timer();
	private Solenoid servoesToLookAt;
	private double timeToRun;
	private boolean isTriggered = false;
	
	SolenoidAccelerator (double time, Solenoid solenoidToLookAt) {
		this.servoesToLookAt = solenoidToLookAt;
		this.timeToRun = time;
		return;
	}
	
	/**
	 * @see org.usfirst.frc.team2239.robot.Action#run()
	 * @return true if the rotation is complete or false if the rotation is not complete
	 */
	public boolean run()
	{
		if (!isTriggered ) {
			
			timer.start();
			isTriggered = true;
		}
		
		// If the timer has not yet expired...
		if (timer.get() <= timeToRun){
			servoesToLookAt.set(true);
			
		} else {
			// Timer has expired, so end the action
			servoesToLookAt.set(false);
			timer.reset();
			isTriggered = false;
		}
		return false;
	}
	 
}

package org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;

public class GrabberAccelerator implements Action {
	private Timer timer = new Timer();
	private SpeedControllerGroup motorsToLookAt;
	private double speed;
	private double timeToRun;
	private boolean hasStarted = false;
	
	GrabberAccelerator (double time, double speed, SpeedControllerGroup motorsToLookAt) {
		this.motorsToLookAt = motorsToLookAt;
		this.speed = speed;
		this.timeToRun = time;
		return;
	}
	
	/**
	 * @see org.usfirst.frc.team2239.robot.Action#run()
	 * @return true if the rotation is complete or false if the rotation is not complete
	 */
	public boolean run()
	{
		if (!hasStarted ) {
			System.out.println("Starting GrabberAccelerator("+ timeToRun +", "+speed+")");
			timer.start();
			hasStarted = true;
		}
		
		// If the timer has not yet expired...
		if (timer.get() <= timeToRun){
			motorsToLookAt.set(speed);
		} else {
			// Timer has expired, so end the action
			motorsToLookAt.set(0);
			timer.reset();
			return true;
		}
		return false;
	}
	 
}

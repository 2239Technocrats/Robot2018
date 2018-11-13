package org.usfirst.frc.team2239.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;

public class LiftAccelerator implements Action {
	private Timer timer = new Timer();
	private WPI_TalonSRX motor;
	private double speed;
	private double timeToRun;
	private boolean hasStarted = false;
	
	LiftAccelerator (double time, double speed, WPI_TalonSRX motor) {
		this.motor = motor;
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
			motor.set(speed);
		} else {
			// Timer has expired, so end the action
			motor.set(0);
			timer.reset();
			return true;
		}
		return false;
	}
	 
}

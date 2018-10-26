package org.usfirst.frc.team2239.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Solenoid;

//TODO finish documentation and changing this from rotationAccelerator into the EncoderAccelerator
//TODO upgrade (this is just something that doesn't need to be done, but would make the program better.) Make a Accelerator class or framework that both of these are just versions of.


public class AutonomousAccelerator implements Action {
	
	private Timer timer;
	private SpeedControllerGroup left;
	private SpeedControllerGroup right;
	private WPI_TalonSRX encoderLeft;
	private WPI_TalonSRX encoderRight;
	private SpeedControllerGroup grabberWheels;
	private WPI_TalonSRX lift;
	private Solenoid grabberSolenoid;
	private double leftSpeed;
	private double rightSpeed;
	private double grabberWheelsSpeed;
	private double liftSpeed;
	private boolean grabberState;
	private double leftDistance;
	private double rightDistance;
	private double time;
	private double moveTicks = 4*Math.PI*1024;
	public AutonomousAccelerator(SpeedControllerGroup left, SpeedControllerGroup right, SpeedControllerGroup grabberWheels, WPI_TalonSRX lift, WPI_TalonSRX encoderLeft, WPI_TalonSRX encoderRight, Solenoid grabberSolenoid, double leftSpeed, double rightSpeed, double grabberWheelsSpeed, double liftSpeed,  boolean grabberState, double leftDistance, double rightDistance, double time) {
		this.left = left;
		this.right = right;
		this.encoderLeft = encoderLeft;
		this.encoderRight = encoderRight;
		this.grabberWheels = grabberWheels;
		this.lift = lift;
		this.grabberSolenoid = grabberSolenoid;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.grabberWheelsSpeed = grabberWheelsSpeed;
		this.liftSpeed = liftSpeed;
		this.grabberState = grabberState;
		this.leftDistance = leftDistance;
		this.rightDistance = rightDistance;
		this.time = time;
	}
	@Override
	public boolean run() {
		
		encoderLeft.setSelectedSensorPosition(0,0,100);
		encoderRight.setSelectedSensorPosition(0,0,100);
		
		if(time > 0){
			timer.start();
			while(timer.get() < time){
				left.set(leftSpeed);
				right.set(rightSpeed);
				grabberWheels.set(grabberWheelsSpeed);
				lift.set(liftSpeed);
				grabberSolenoid.set(grabberState);
			}
			timer.reset();
			return true;
		}
		else if(leftDistance > 0 || rightDistance > 0){
			while(getEncoderValue(encoderLeft) >= leftDistance && getEncoderValue(encoderRight) >= rightDistance){
				System.out.println("current encoder value left: " + getEncoderValue(encoderLeft));
				System.out.println("current encoder value right: " + getEncoderValue(encoderRight));
				left.set(leftSpeed);
				System.out.println("Left speed:" + leftSpeed);
				right.set(rightSpeed);
				grabberWheels.set(grabberWheelsSpeed);
				System.out.println("grabberWheelsSpeed" + grabberWheelsSpeed);
				lift.set(liftSpeed);
				System.out.println("lift speed: " + liftSpeed);
				grabberSolenoid.set(grabberState);
				System.out.println("grabberState: " + grabberState);
			}
			return true;
		}
		else if(leftDistance < 0 || rightDistance < 0){
			while(getEncoderValue(encoderLeft) <= leftDistance*moveTicks && getEncoderValue(encoderRight) <= rightDistance*moveTicks){
				left.set(leftSpeed);
				System.out.println("Left speed:" + leftSpeed);
				right.set(rightSpeed);
				grabberWheels.set(grabberWheelsSpeed);
				System.out.println("grabberWheelsSpeed" + grabberWheelsSpeed);
				lift.set(liftSpeed);
				System.out.println("lift speed: " + liftSpeed);
				grabberSolenoid.set(grabberState);
				System.out.println("grabberState: " + grabberState);
			}
			return true;
		}
		else
		{
			left.set(0);
			right.set(0);
			grabberWheels.set(0);
			lift.set(0);
			grabberSolenoid.set(false);
			return false;
		}
	}
	public double getEncoderValue(WPI_TalonSRX encoderToGet) {
		return -encoderToGet.getSelectedSensorPosition(0);
	}
}
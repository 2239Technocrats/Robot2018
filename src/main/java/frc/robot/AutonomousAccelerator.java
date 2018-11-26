package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Solenoid;

//TODO finish documentation and changing this from rotationAccelerator into the EncoderAccelerator
//TODO upgrade (this is just something that doesn't need to be done, but would make the program better.) Make a Accelerator class or framework that both of these are just versions of.


public class AutonomousAccelerator implements Action {
	
	private Timer timer;
	private TechnoDrive driveTrain;
	private WPI_TalonSRX[] encoderMotors;
	private SpeedControllerGroup grabberWheels;
	private WPI_TalonSRX lift;
	private Solenoid grabberSolenoid;
	private double leftSpeed;
	private double rightSpeed;
	private double grabberWheelsSpeed;
	private double liftSpeed;
	private boolean grabberState;
	private double distance;
	private double time;
	private double moveTicks = 4*Math.PI*1024;
	public AutonomousAccelerator(TechnoDrive driveTrain, SpeedControllerGroup grabberWheels, WPI_TalonSRX lift, WPI_TalonSRX[] encoderMotors, Solenoid grabberSolenoid, double leftSpeed, double rightSpeed, double grabberWheelsSpeed, double liftSpeed,  boolean grabberState, double distance, double time) {
		this.driveTrain = driveTrain;
		this.encoderMotors = encoderMotors;
		this.grabberWheels = grabberWheels;
		this.lift = lift;
		this.grabberSolenoid = grabberSolenoid;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.grabberWheelsSpeed = grabberWheelsSpeed;
		this.liftSpeed = liftSpeed;
		this.grabberState = grabberState;
		this.distance = distance*moveTicks;
		this.time = time;
	}
	@Override
	public boolean run() {
		
		for(WPI_TalonSRX motor : encoderMotors){
			motor.setSelectedSensorPosition(0, 0, 100);
		}
		
		if(time > 0){
			timer.start();
			while(timer.get() < time){
				driveTrain.tankDrive(leftSpeed, rightSpeed);
				grabberWheels.set(grabberWheelsSpeed);
				lift.set(liftSpeed);
				grabberSolenoid.set(grabberState);
			}
			timer.reset();
			return true;
		}
		else if(distance > 0){
			while(getEncoderValue(encoderMotors[0]) > distance || getEncoderValue(encoderMotors[1]) > distance){
				System.out.println("current encoder value left: " + getEncoderValue(encoderMotors[0]));
				System.out.println("current encoder value right: " + getEncoderValue(encoderMotors[1]));
				System.out.println("Left speed:" + leftSpeed);
				driveTrain.tankDrive(leftSpeed, rightSpeed);
				grabberWheels.set(grabberWheelsSpeed);
				System.out.println("grabberWheelsSpeed" + grabberWheelsSpeed);
				lift.set(liftSpeed);
				System.out.println("lift speed: " + liftSpeed);
				grabberSolenoid.set(grabberState);
				System.out.println("grabberState: " + grabberState);
			}
			return true;
		}
		else if(distance < 0){
			while(getEncoderValue(encoderMotors[0]) < distance || getEncoderValue(encoderMotors[1]) < distance){
				driveTrain.tankDrive(leftSpeed, rightSpeed);
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
			driveTrain.tankDrive(0, 0);;
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
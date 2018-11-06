package org.usfirst.frc.team2239.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

//TODO finish documentation and changing this from rotationAccelerator into the EncoderAccelerator
//TODO upgrade (this is just something that doesn't need to be done, but would make the program better.) Make a Accelerator class or framework that both of these are just versions of.

public class EncoderAccelerator implements Action {
	
	public static int ENCODER_CLOSED_LOOP_PRIMARY = 0;
	public static int ENCODER_CLOSED_LOOP_CASCADING = 1;
	
	TechnoDrive driveTrain;
	WPI_TalonSRX[] valueMotors;
	//the biggest values we're driving with i.e. tankDrive(-maxVelocity, maxVelocity).
	//Must be in between -1 and 1. Negative if going counter-clockwise
	double maxVelocity;
	double curVelocity = 0; //init to 0; we shouldn't be moving when we initiate //positive if moving forwards
	double rollPastDecrease = .15; //how much we decrease maxVelocity by if we overshoot.
	double accelerate = .05; //how quickly @param velocity will change
	double offset = .36; //the lowest power the motors should ever be at //always positive
	double tolerance = 20; //How close (in ticks) to the final destination should you get before stopping (should not be 0. Perfection is impossible.)
	double moveTicks; //how much to move, in inches (positive means forwards)
	//with 107 ticksPerInch, this thing tried to go 10 inches and instead went 22
//	double ticksPerInch = 81.5; //used to be 53.5 on the practice bot 
	double ticksPerInch = 4*Math.PI*1024*(50/34);//115.5 inch
	double targetDistance; //the encoder value we aspire to be at when done.
	double maxVelocityTicks = 2000; //The ticks traveled at which we start to decrease velocity at //always positive
	double liftWheelDiameter = 7/8;//TODO decide
//	double liftWheelDiameter = 65/32;//TODO decide
	double idk = 2655*.0784*Math.PI*liftWheelDiameter*1024;//lift motor rpm = 2655, lift motor gear ratio .0784, ticks per rotation 1024
	double liftWheelModifier = idk; //The difference in speed between the main motors and the motor that controls the lift
	boolean forward; //true if we should be moving forwards, false otherwise (still or moving backwards)	
	private boolean hasStarted = false;
	
	
	public EncoderAccelerator (TechnoDrive driveTrain, WPI_TalonSRX[]  motorsToLookAt, double distance, double maxVelocity) {
		this(driveTrain, motorsToLookAt, distance, maxVelocity, false);
	}

	public EncoderAccelerator (TechnoDrive driveTrain, WPI_TalonSRX[]  motorsToLookAt, double distance, double maxVelocity, boolean isLiftMotor) {
		this.forward = (distance>0);
		this.driveTrain = driveTrain;
		this.valueMotors = motorsToLookAt;
		this.moveTicks = distance*ticksPerInch;	
		if (isLiftMotor){
			this.moveTicks = distance*ticksPerInch*liftWheelModifier;	
		}
		
		//double encValue = getEncoderValue();
		this.targetDistance = this.moveTicks; //encValue+this.moveTicks;
		this.maxVelocity = maxVelocity;
		
//		throw new RuntimeException("Starting encoder value = "+encValue+", Move ticks = " + this.moveTicks + ", Target distance = "+this.targetDistance);
	}
	
	
	/**
	 * @see org.usfirst.frc.team2239.robot.Action#run()
	 * @return true if the rotation is complete or false if the rotation is not complete
	 */
	public boolean run()
	{
		if (!hasStarted) {
			System.out.println("Starting EncoderAccelerator("+ this.moveTicks +")");
			for (int i = 0; i < valueMotors.length; i++) {
				valueMotors[i].setSelectedSensorPosition(0, ENCODER_CLOSED_LOOP_PRIMARY, 100);
			}
			hasStarted = true;
			return false;
		}
		
//		System.out.println("Im actually moving straight!");
		double curValue = getEncoderValue();
//		System.out.println("curValue: " + curValue);
		double offDistance = (targetDistance-curValue);
//		System.out.println("I'm this far off: "+offDistance);
//		System.out.println("targetDistance: "+targetDistance);
		if (targetDistance-tolerance < curValue && curValue < targetDistance+tolerance) { //we did it!
			System.out.println("leftEncoderValue: " + valueMotors[0].getSelectedSensorPosition(ENCODER_CLOSED_LOOP_PRIMARY));
			System.out.println("rightEncoderValue: " + valueMotors[1].getSelectedSensorPosition(ENCODER_CLOSED_LOOP_PRIMARY));
			System.out.println("curValue: " + curValue);
			driveTrain.tankDrive(0, 0); //stop driving //TODO this causes a lot of slippage when moving forwards and backwards
			return true;
		}
		
		boolean shouldBeForwards = offDistance>0;
		if (shouldBeForwards!=forward) {
			maxVelocity = Math.max(maxVelocity - rollPastDecrease, offset);
//			curVelocity = 0; //stop it from swinging past
			driveTrain.tankDrive(0, 0); // Actually stop it if we're not doing the back up code
			System.out.println("Swung past the target!");
			return true; // This line disables the backup code!!! Comment out to re-enable it.
		}
		forward = shouldBeForwards;
		
		double targetVelocity;
		if (forward) {
			targetVelocity = Math.min(((maxVelocity-offset)/maxVelocityTicks)*offDistance+offset, maxVelocity);
		} else {
			targetVelocity = Math.max(((maxVelocity-offset)/maxVelocityTicks)*offDistance-offset, -maxVelocity);
		}
		
//		System.out.println("Target velocity before setting is: "+targetVelocity);
//		System.out.println("curVelocity before setting is: "+curVelocity);
		if (forward) {
//			System.out.println("We're going forwards");
			System.out.println("leftEncoderValue: " + valueMotors[0].getSelectedSensorPosition(ENCODER_CLOSED_LOOP_PRIMARY));
			System.out.println("rightEncoderValue: " + valueMotors[1].getSelectedSensorPosition(ENCODER_CLOSED_LOOP_PRIMARY));
			System.out.println("curValue: " + curValue);
			if (targetVelocity > curVelocity+accelerate) { //if I'm going slower than I should, ramp up to it
				curVelocity = curVelocity+accelerate;
			} else {
				curVelocity = targetVelocity; //this handles deceleration with the proportionality stuff
			}
			curVelocity = Math.min(curVelocity, maxVelocity);
		} else {
//			System.out.println("We're going counterclockwise");
			if (targetVelocity < curVelocity-accelerate) { //if I'm going slower than I should, ramp up to it
				curVelocity = curVelocity-accelerate;
			} else {
				curVelocity = targetVelocity; //this handles deceleration with the proportionality stuff
			}
			curVelocity = Math.max(curVelocity, -maxVelocity);
		}
//		System.out.println("Target velocity is: "+targetVelocity);
//		System.out.println("Actually driving at: " + curVelocity);
		driveTrain.tankDrive(curVelocity, curVelocity*.65); //actually drive
		return false;
	}

	public double getEncoderValue() {
		//TODO don't just average - also check to see if any of the encoders are way off or may be broken and ignore those ones.
		double sum = 0.0;
		double reading = 0.0;
		for (WPI_TalonSRX motor : this.valueMotors) {
			
			reading = motor.getSelectedSensorPosition(ENCODER_CLOSED_LOOP_PRIMARY);
			sum += reading;
//			System.err.println("Encoder reading: " + reading);
		}
		double avg = sum/this.valueMotors.length; //compute the average encoder value
//		System.out.println("the average encoder values from sensors: " + avg);
		return -avg; //Had to put a negative sign because the encoder is giving opposite values 
	}
}

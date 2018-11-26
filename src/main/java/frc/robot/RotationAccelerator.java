package frc.robot;

import com.kauailabs.navx.frc.AHRS;

public class RotationAccelerator implements Action {
	TechnoDrive driveTrain;
	AHRS navSensor;
	//the biggest values we're driving with i.e. tankDrive(-maxVelocity, maxVelocity).
	//Must be in between -1 and 1. Negative if going counter-clockwise
	double maxVelocity;
	double curVelocity = 0; //init to 0; we shouldn't be moving when we initiate //positive if turning clockwise
	double swingPastDecrease = .1; //how much we decrease maxVelocity by if we overshoot.
	double accelerate = .05; //how quickly @param velocity will change
	double offset = .5; //the lowest power the motors should ever be at //always positive
	double tolerance = 2; //How close to the final orientation should you get before stopping (degrees) (should not be 0. Perfection is impossible.)
	double turnAngle; //how much to turn (in degrees, positive means clockwise)
	double targetAngle; //the angle we aspire to be at. This can be greater than 2Pi //TODO test if this can be negative
	double maxVelocityAngle = 30; //The angle (in degrees) we start to decrease velocity at //always positive
	boolean clockwise; //whether or not the turnAngle is a clockwise angle
	private boolean hasStarted = false;
	
	
	
	public RotationAccelerator (TechnoDrive driveTrain, AHRS navSensor, double turnAngle, double maxVelocity) {
		this.clockwise = (turnAngle>0);
		this.driveTrain = driveTrain;
		this.navSensor = navSensor;
		this.turnAngle = turnAngle;
		this.targetAngle = turnAngle; //getAngle()+turnAngle;
		this.maxVelocity = maxVelocity;
	}
	
	
	/**
	 * @see org.usfirst.frc.team2239.robot.Action#run()
	 * @return true if the rotation is complete or false if the rotation is not complete
	 */
	public boolean run()
	{
		if (!hasStarted ) {
			System.out.println("Starting RotationAccelerator("+this.targetAngle+")");
			navSensor.reset();
			hasStarted = true;
			return false;
		}

//		System.out.println("Im actually rotating!");
		double curAngle = getAngle();
		double offAngle = (targetAngle-curAngle);
//		System.out.println("I'm this far off: "+offAngle);
		if (targetAngle-tolerance < curAngle && curAngle < targetAngle+tolerance) { //we did it!
			driveTrain.tankDrive(0, 0); //stop driving
			return true;
		}
		
		boolean shouldBeClockwise = offAngle>0;
		if (shouldBeClockwise!=clockwise) {
			maxVelocity = Math.max(maxVelocity - swingPastDecrease, offset);
			curVelocity = 0; //stop it from swinging past
//			System.out.println("Swung past the target!");
		}
		clockwise = shouldBeClockwise;
		
		double targetVelocity;
		if (clockwise) {
			targetVelocity = Math.min(((maxVelocity-offset)/maxVelocityAngle)*offAngle+offset, maxVelocity);
		} else {
			targetVelocity = Math.max(((maxVelocity-offset)/maxVelocityAngle)*offAngle-offset, -maxVelocity);
		}
		
//		System.out.println("Target velocity before setting is: "+targetVelocity);
//		System.out.println("curVelocity before setting is: "+curVelocity);
		if (clockwise) {
//			System.out.println("We're going clockwise");
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
		driveTrain.tankDrive(curVelocity, -curVelocity); //actually drive
		return false;
	}
	
	public double getAngle() {
//		System.out.println("the degrees from nav sensor: " + navSensor.getAngle());
		return navSensor.getAngle();
		 
	}
}

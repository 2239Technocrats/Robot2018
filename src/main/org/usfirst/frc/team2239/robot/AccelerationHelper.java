package org.usfirst.frc.team2239.robot;

public class AccelerationHelper {
	TechnoDrive theRobot;
	double startTime;
	double maxVelocity;
	double accelerateTime;
	double endTime;
	double curVelocity;
	double decelerateTime;
	double topSpeed;
	double distance;
	
	
	/*
	 * 
	 * @params
	 * theRobot: the robot object so that has a tankDrive() method (so we can drive the robot)
	 * startTime: the time the timer is at when we start running acceleration
	 * runTime: how long you want the robot to run for
	 * maxVelocity: the biggest value you want passed into tankDrive (this should be negative if going backwards)
	 * acceleration: how quickly the robot should accelerate. (this should be negative if going backwards)
	 * emergencyAcceleration: if we've gone over the time to stop, use this acceleration to stop really quickly
	 */
	public AccelerationHelper (TechnoDrive theRobot, double startTime, double distance, double maxVelocity) {
		//set defaults
		if (maxVelocity==-1) maxVelocity = .5;
		
		this.theRobot = theRobot;
		this.startTime = startTime;
		this.maxVelocity = maxVelocity;
		this.distance = distance;
		this.curVelocity = 0; //init to 0; we shouldn't be moving when we initiate
		this.accelerateTime = .5;
		this.decelerateTime = .2;
		this.topSpeed = 100; //Inches per second
		this.endTime = startTime  + this.distanceToTime(distance);
	
	}
	
	//computes the travel time given a distance.
	public double distanceToTime(double distance){
		double ans;
		if (distance > (accelerateTime + decelerateTime) * topSpeed/2){
			ans = ((distance - ((topSpeed/2)* (accelerateTime + decelerateTime)))/topSpeed)+(accelerateTime + decelerateTime);
		} else {
			ans = Math.sqrt(((2 * distance) * (accelerateTime + decelerateTime))/topSpeed);
		}
	    return ans;
	}
	//make a new function called accelerate which takes
	//double curTime (the current time gotten from the timer)
	//
	//check to see if the time is less than the middle time
	//if so, increase curVelocity by acceleration
	//if not, decrease by acceleration
	//Either way, make sure we don't go below 0 or over maxVelocity
	//Drive by curVelocity
	public void accelerate(double curTime)
	{
		//curTime now represents the time since the start of this movement.
		curTime = curTime - startTime;
		if (endTime >(accelerateTime + decelerateTime)) {
			if (curTime < accelerateTime) //if in the acceleration phase of the trip
			{
				curVelocity = curTime/accelerateTime * maxVelocity; //accelerate up to max	
			} else if (curTime > endTime - decelerateTime && curTime < endTime) { //if in the deceleration phase of the trip
				curVelocity = (endTime - curTime)/decelerateTime * maxVelocity; //decelerate to 0
			} else if (curTime > endTime){ //past the time that we should have stopped
				curVelocity = 0; //decelerate super quickly to 0 using emergencyAcceleration
			} else curVelocity = maxVelocity;
			}
		else {
			if (curTime < (accelerateTime * endTime)/(accelerateTime + decelerateTime)) //if in the acceleration phase of the trip
			{
				curVelocity = curTime/accelerateTime * maxVelocity; //accelerate up to max	
			} else if (curTime < endTime) { //if in the deceleration phase of the trip
				curVelocity = (endTime - curTime)/decelerateTime * maxVelocity; //decelerate to 0
			} else if (curTime > endTime){ //past the time that we should have stopped
				curVelocity = 0; //decelerate super quickly to 0 using emergencyAcceleration
			} else curVelocity = maxVelocity;
		}
		theRobot.tankDrive(curVelocity, curVelocity); //drive the bot
	}
	
}

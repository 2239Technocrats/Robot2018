package org.usfirst.frc.team2239.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class GearStateMachine {
	
	public Action[] futureActions = new Action[] {}; //TODO delete old comments about state
	public double attackAngle = Math.toRadians(10); //Angle (radians) the physical robot must be within from the peg in order to start charging
	public double chargeDist = 70; //Distance (inches) the physical robot must be within from the peg in order to start charging
	public TechnoDrive driveTrain;
	public AHRS navSensor;
	public TalonSRX[] motorsToLookAt;
	public double overChargeAmt = -5; //How many inches it should try to overcharge when placing a gear
	
	public GearStateMachine(TechnoDrive driveTrain, AHRS navSensor, TalonSRX[] motorsToLookAt) {
		this.driveTrain = driveTrain;
		this.navSensor = navSensor;
		System.out.println("Navtest "+this.navSensor.getAngle());//TODO delete
		this.motorsToLookAt = motorsToLookAt;
	}
	
	/*
		Get a good view
		if (theta (the angle the between the robot, the peg and the wall the peg is on) (a.k.a. anti-attack angle) is big enough) {
			if (close enough) {
				point at the peg
				charge towards the peg
			} else {
				go halfway to the peg (or go to approach point)
				point towards peg
				restart
			}
			
		} else {
			if (too far away) {
				point towards the approach point (also called "away" or "away point")
				move halfway to the approach point (or go to approach point)
				point towards peg
				restart
			} else {
				move to the approach point (usually about 3 feet out from the point of the peg)
				point towards the peg again
				restart
			}
		}
	*/
	
	//RotationAccelerator (TechnoDrive driveTrain, AHRS navSensor, double turnAngle, double maxVelocity)
	//EncoderAccelerator (TechnoDrive driveTrain, CANTalon[] motorsToLookAt, double distance, double maxVelocity) {

	
//	public void computeNextAction(double theta, double distToPeg, double rotationToPeg) {	
//		if (futureActions.length==0) {
//			
//			
//			if (Math.abs(((Math.PI/2)-theta))<=attackAngle) {
//				if (distToPeg<chargeDist) {
//					System.out.println("Navtest "+navSensor.getAngle());//TODO delete
//					futureActions = new Action[] {new RotationAccelerator(driveTrain, navSensor, Math.toDegrees(rotationToPeg), .8), new EncoderAccelerator(driveTrain, motorsToLookAt, distToPeg+overChargeAmt, .8)};
//				}
//			}
//		}
//	}
	
	public Action getNextAction() {
		//Just return the next accelerator in the sequence and update the sequence
		if (futureActions.length==0) return null;
		// if no farther to move return null
		
		//make a new futureAccelerators that just doesn't have the first accelerator
		//we will return that first accelerator so the robot can run it
		Action[] newFutureActions = new Action[futureActions.length-1];
		for (int i=1; i<futureActions.length; i++) {
			newFutureActions[i-1] = futureActions[i];
		}
		Action ans = futureActions[0]; //return the next accelerator in the sequence!
		futureActions = newFutureActions;
		return ans;
	}
}

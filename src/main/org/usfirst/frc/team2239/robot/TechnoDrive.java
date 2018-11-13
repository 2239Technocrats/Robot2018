package main.org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Utility for driving the robot
 * Only put functions that operate instantaneously in this class
 *
 * Author: Dean Bassett and London Lowmanstone
 */


public class TechnoDrive extends DifferentialDrive {
    public static final double MAIN_SPEED = 1;
    
//
    public TechnoDrive(SpeedControllerGroup left, SpeedControllerGroup right) {
    	super(left, right);
    }
    
    /*
     * Goal: Write a program to ramp up at acceleration until halfway there, and then ramp down afterwards
     * 
     */
    public double autoRamp(double acceleration, double rotationAmt, double mySpeed) {
    	int curEncoderCount = 0; //dummy variable //how many times the wheels have rotated.
    	//tankDrive(mySpeed, mySpeed); 
    	if ((mySpeed < 1) && (curEncoderCount < rotationAmt/2))
    	{
    		mySpeed += acceleration;
    	}
    	
    	if  (curEncoderCount >= rotationAmt/2)
    	{
    		mySpeed -= acceleration;
    	}
    	
    	tankDrive(mySpeed, mySpeed);
    	return mySpeed;
    }

  
 
    public void tankDrive(GenericHID leftStick, GenericHID rightStick) {
        if (leftStick == null || rightStick == null) {
            throw new NullPointerException("Null HID provided");
        }

        double left = -leftStick.getY();
        double right = -rightStick.getY();
        tankDrive(left, right);
    }

    @Override
    public void tankDrive(double left, double right) {
        tankDrive(left, right, true);
    }

    @Override
    public void tankDrive(double left, double right, boolean squared) {
//    	System.out.println("What the tankDrive is doing: " +left+ " " +right);
        super.tankDrive(-left, -right, squared);
    }
    
    public void tankDrive(XboxController controller, double speed) {
    	tankDrive(-controller.getY(XboxController.Hand.kLeft)*speed, -controller.getY(XboxController.Hand.kRight)*speed*.65);
    	// gets y position from both right and left joysticks 
    }
    
    public void tankDrive(XboxController controller) {
    	tankDrive(controller, 1);
    	//whatever value y gives for speed, max that value
    	//so if controller is .8 forward drive at a full 80% speed
    }
    
   
    /*
     * curRotation current roatation in degrees from the nav sensor's getAngle() function
     */
    public void driveStraight (double curRotation, double wantedRotation, double velocity){ //TODO do not know directions
    	double ADJUST_RATE = 1; //number from 0 to 1: how much it corrects
    	
    	curRotation%=360; //set it to value between 0 and 359
    	wantedRotation%=360;
    	double adjust;//what you can add to curRotation to get wantedRotation
    	if (curRotation>wantedRotation) {
    		if (curRotation-wantedRotation<=180) {
    			adjust = wantedRotation-curRotation;
    		} else {
    			//for example, if curRotation is 359 and wantedRotation is 1
    			adjust = 360-curRotation+wantedRotation;//go the other way around
    		}
    	} else  { //(curRotation<=wantedRotation)
    		if ((wantedRotation-curRotation)<=180) {
    			adjust = wantedRotation-curRotation;
    		} else { //for example, if curRotation is 1 and wantedRotation is 359
    			adjust = wantedRotation-curRotation-360;//go the other way around
    		}
    	}
    	double turnAmount = adjust/360.0;
    	if (turnAmount==0) {
    		tankDrive(velocity, velocity); //a miracle has occurred and the robot is going perfectly straight
    	} else if ((velocity>0 && turnAmount<0) || (velocity<0 && turnAmount>0)) { //need to turn clockwise //TODO check direction
    		tankDrive(velocity*(1-turnAmount*ADJUST_RATE), velocity);
    	} else { //need to turn counterclockwise //TODO check direction
    		tankDrive(velocity, velocity*(1-turnAmount*ADJUST_RATE));
    	}
	}
}

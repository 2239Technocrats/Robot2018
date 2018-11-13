package org.usfirst.frc.team2239.robot;

public class VisionHelper { //create a class to do the math

	/*
	 * @param halfFov is half the field of view in radians 
	 * 
	 */
	static public double getDistanceToTape(double pixelTapeHeight, double pixelScreenHeight, double realTapeHeight, double halfFov) {
		double realScreenHeight = (realTapeHeight*pixelScreenHeight)/pixelTapeHeight;
		return realScreenHeight/(2*Math.tan(halfFov));
	}
	
	/*
	 * @param left robot's distance to the left piece of tape (inches)
	 * @param right robot's distance to the right piece of tape (inches)
	 * @param spread the real distance between the two pieces of tape (inches)
	 * returns {horiz distance to  get to peg, vertical distance to get to peg}
	 * 
	 * TODO if the robot is within the two pieces of tape, this might not be accurate, but it doesn't matter because it will be close enough
	 */
	static public double[] getPositionToGoal(double left, double right, double spread) {
		double x1;
		double dy;
		/*
		if (left<right) { //we're to the left of the peg
			//just swapping right and left
			double temp = right;
			right = left;
			left = temp;
		}	
		*/
		//Calculate the parallel (to the peg's wall) distance between the robot and the closer tape
		x1 = (left*left - right*right - spread*spread) / (2*spread);
		System.out.println("x1: "+x1);
		dy = Math.sqrt(Math.abs(right*right - x1*x1)); //Calculate perpendicular distance between the robot and the peg

		double dx;
		//Calculate the parallel distance between the robot and the peg
		dx = -x1 - spread/2;
		double[] ans = {dx, dy}; //Create an array to send the answers back to the program
		return ans; //Send answer back to the main program
	}
	
	/*
	 * @params
	 * dx inches parallel to peg base to get to peg
	 * dy inches perpendicular to peg base to get to peg
	 * returns the distance to the peg
	 */
	static public double getDistanceToGoal(double dx, double dy)	{
		return Math.sqrt(dx*dx + dy*dy); //Calculate the hypotenuse of triangle with non-hypotenuse side lengths of dx and dy.
	}
	
	//TODO reorder parameters, make sure constants are defined.
	/*
	 * @params
	 * dx inches parallel to peg base to get to peg
	 * dy inches perpendicular to peg base to get to peg
	 * middleXPixel the pixel position of the peg on the screen (the middle of the two pieces of tape). Always positive because screen coordinates are always positive
	 * pixelScreenWidth the width of the screen in pixels
	 * halfXFov half the field of view for the x (radians)
	 * away how far away (in inches) from the peg our target is in the y (perpendicular to peg base) direction
	 * returns double[3] ans
	 * ans[0] the angle to turn to point towards the target (radians)
	 * ans[1] the distance to travel to hit the target (inches)
	 * ans[2] the angle to turn to point towards the peg (radians)
	 * ans[3] theta: the angle from the wall to the peg to the robot
	 */
	
	static public double[] getValuesToTarget(double dx, double dy, double middleXPixel, double pixelScreenWidth, double halfXFov, double away) {
		//All angles in here have a direction. Positive is clockwise.
		double dc = getDistanceToGoal(dx, dy); //compute the distance to the peg in inches
		//the angle from the robot to the peg to the wall the peg is on
		double theta = Math.acos(dx/dc); //this is also the angle from peg to robot to the horizontal line (parallel to peg base) the robot is on
		double pixelsOffCenter = middleXPixel-.5*pixelScreenWidth; //how many pixels we are are looking from the center of the tape
		//Same as (halfXFov*pixelsOffCenter)/(.5*pixelScreenWidth)
		double alpha = (2.0*halfXFov*pixelsOffCenter)/pixelScreenWidth; //the angle in radians to turn to the peg
		System.out.println("Angle to turn to point towards the peg in degrees: "+Math.toDegrees(alpha));
		System.out.println("What I'm putting into atan: "+((dy-away)/dx));
		//double b2t = -Math.atan((dy-away)/dx); //angle from the horizontal line to the robot to the target
		//System.out.println("b2t: "+b2t);
		double a1 = alpha+theta+theta; //angle the robot needs to turn to point at the target
		//The reason for the sign "(b2t/Math.abs(b2t))" term is because a2 always needs to be in the direction of b2t
		double a2 = (theta/Math.abs(theta))*(Math.PI/2)-theta; //angle the robot should turn after it hits the target to point towards the peg
		System.out.println("The three values coming up:");
		System.out.println(a1);
		System.out.println(dx/Math.cos(theta));
		System.out.println(a2);
		return new double[] {a1, dx/Math.cos(theta), a2, theta}; //TODO add theta into comment --> //{ans[0] the angle to turn to point towards the target (radians), the distance to travel to hit the target (inches), the angle to turn to point towards the peg (radians)}
	}
	
	static public double[] getValuesToPeg(double dx, double dy, double middleXPixel, double pixelScreenWidth, double halfXFov) {
		return getValuesToTarget(dx, dy, middleXPixel, pixelScreenWidth, halfXFov, 0);
	}
	
	//takes two doubles; the heights of the tape
	//takes a third double - a constant that we multiply by the ratio
	//returns the radian angle to the peg
	//should print out the degree angle as it goes
	static public double angleDegree (double height1, double height2, double constant){
		double ratio = (height1 - height2) / (height1 + height2) ;
		double ans = ratio * constant; 
		System.out.println("Angle is " + ans + " degrees");
		return  Math.toRadians(ans);
	}
}

package frc.robot;

import main.org.usfirst.frc.team2239.robot.WPI_TalonSRX;

public class TechnoDriveMock {
	private double rightencoder = 0;
	private double leftencoder = 0;
	public TechnoDriveMock() {
		
	}
	
	public void tankDrive(double left, double right) {
		tankDrive(left, right, true);
	}
	
	public void tankDrive(double left, double right, boolean squared) {
		
	}
	
	public void tankDrive(XboxController controller, double speed) {
		
	}
	
	public void tankDrive(XboxController controller) {
		tankDrive(controller, 1);
	}
	
	
    public double [] getEncoderValues() {
    	double [] readings = {};
    	double sum = 0.0;
    	int i = 0;
    	for (WPI_TalonSRX motor : this.encoderMotors) {
			
			readings[i] = motor.getSelectedSensorPosition(ENCODER_CLOSED_LOOP_PRIMARY);
			i+=1;

		}
    	
    	return readings;
    }

}

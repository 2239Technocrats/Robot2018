package frc.robot;

import main.org.usfirst.frc.team2239.robot.robot.EncoderAccelerator;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class EncoderAcceleratorTest{
	TechnoDrive driveTrain;
	WPI_TalonSRX[]  motorsToLookAt;
	double distance;
	double maxVelocity;
	
	@beforeclass
	private void setup() {

	}

	@before
	private void newTestEnvironment() {
		EncoderAccelerator testEA = new EncoderAccelerator()
	}

}
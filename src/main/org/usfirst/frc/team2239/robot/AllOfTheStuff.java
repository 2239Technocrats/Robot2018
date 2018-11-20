package main.org.usfirst.frc.team2239.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class AllOfTheStuff {
	Solenoid[] solenoids = new Solenoid[3];
	WPI_TalonSRX[] talons = new WPI_TalonSRX[11];
	SpeedControllerGroup[] sides = new SpeedControllerGroup[2];
	Compressor compressor;
	WPI_TalonSRX[] encoderMotors;
	public AllOfTheStuff(){}
	public void addTalon(WPI_TalonSRX motor, int index){
		talons[index] = motor;
	}
	public void addSolenoid(Solenoid solenoid, int index){
		solenoids[index] = solenoid;
	}
	public void addSide(SpeedControllerGroup side, int index){
		sides[index] = side;
	}
	public void addCompressor(Compressor compressor){
		this.compressor = compressor;
	}
	public void addEncoderMotors(WPI_TalonSRX[] encoderMotors){
		this.encoderMotors = encoderMotors;
	}
	public Solenoid getSolenoid(int index){
		return solenoids[index];
	}
	public WPI_TalonSRX getMotor(int index){
		return talons[index];
	}
	public SpeedControllerGroup getSide(int index){
		return sides[index];
	}
	public Compressor getCompressor(){
		return compressor;
	}
	public WPI_TalonSRX[] getEncoderMotors(){
		return encoderMotors;
	}
}

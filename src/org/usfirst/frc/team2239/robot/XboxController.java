package org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.DriverStation;

public class XboxController extends GenericHID {
	private int port;
	private DriverStation station;

	public XboxController(int port) {
		super(port);
		this.station = DriverStation.getInstance();
	}

	@Override
	public double getX(Hand hand) {
		return hand == Hand.kLeft ? getRawAxis(0) : getRawAxis(4);
	}

	@Override
	public double getY(Hand hand) {
		return hand == Hand.kLeft ? getRawAxis(1) : getRawAxis(5);
	}
	
	@Override
	public double getRawAxis(int which) {
		return station.getStickAxis(port, which);
	}

	@Override
	public boolean getRawButton(int button) {
		return station.getStickButton(port, (byte) button);
	}
	
	// CUSTOM
	public boolean getTrigger(Hand hand) {
		return hand == Hand.kLeft ? getRawAxis(2) > .9 : getRawAxis(3) > .9;
	}
	
	@Override
	public int getPOV(int pov) {
		return station.getStickPOV(port, pov);
	}
	
	//0 if nothing or top button //TODO fix
	//2 if right
	//4 if bottom
	//6 if left
	//in-between numbers are in-between
	public int getSimplePOV() {
		return this.getPOV()/45;
	}
	
	@Override
	public int getPOVCount() {
		throw new UnsupportedOperationException("POVCount is not used");
	}
	
	@Override
	public HIDType getType() {
		throw new UnsupportedOperationException("HIDType is not used");
	}

	@Override
	public String getName() {
		throw new UnsupportedOperationException("getName is not used");
	}

	@Override
	public void setOutput(int outputNumber, boolean value) {
		throw new UnsupportedOperationException("setOutput is not used");
	}

	@Override
	public void setOutputs(int value) {
		throw new UnsupportedOperationException("setOutputs is not used");
		
	}

	@Override
	public void setRumble(RumbleType type, double value) {
		throw new UnsupportedOperationException("setRumble is not used");
	}

}

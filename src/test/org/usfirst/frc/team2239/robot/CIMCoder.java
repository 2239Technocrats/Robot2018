package org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.Encoder;

public class CIMCoder {
	//all imported from another team 
	private Encoder encoder;
	
	public CIMCoder(int channelA, int channelB, boolean isInverted)
	{
		encoder = new Encoder(channelA, channelB, isInverted);
	}
	
	public CIMCoder(int channelA, int channelB, int countsPerRevolution, double wheelDiameter)
	{
		encoder = new Encoder(channelA, channelB);
		//set up new encoders for channel A and B
		encoder.setDistancePerPulse(findDistancePerPulse(countsPerRevolution, wheelDiameter));
		//uses counts per revolution and wheel diameter to find distance per pulse and then sets that as set Distance per pulse
	}
	
	public CIMCoder(int channelA, int channelB, boolean isInverted, int countsPerRevolution, double wheelDiameter)
	{
		encoder = new Encoder(channelA, channelB, isInverted);
		encoder.setDistancePerPulse(findDistancePerPulse(countsPerRevolution, wheelDiameter));
	}
	
	public int getCount()
	{
		return encoder.get();
		//return encoder counts
	}
	
	public double getRate()
	{
		return encoder.getRate();
		//return encoder rate
		
	}

	private double findDistancePerPulse(int countsPerRevolution, double wheelDiameter)
	{		
		return 1/(countsPerRevolution * wheelDiameter * Math.PI);
	}
}
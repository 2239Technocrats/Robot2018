package main.org.usfirst.frc.team2239.robot;
import edu.wpi.first.wpilibj.Solenoid;


public class GearCollectorAction implements Action {

	public Boolean openOrClose;
	public Solenoid gearCollector;
	
	public GearCollectorAction(Solenoid gearCollector, Boolean openOrClose) {
		this.openOrClose = openOrClose;
		this.gearCollector = gearCollector;
	}
	
	@Override
	public boolean run() {
		gearCollector.set(openOrClose);
		return true; //done - it has run
	}

}

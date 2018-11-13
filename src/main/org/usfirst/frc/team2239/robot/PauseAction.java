package main.org.usfirst.frc.team2239.robot;
import edu.wpi.first.wpilibj.Timer;


public class PauseAction implements Action {

	public Timer timer;
	public double targetTime;
	public boolean timerRunning = false;
	
	public PauseAction(double time) {
		this.targetTime = time;
	}
	
	@Override
	public boolean run() {
		if (timerRunning) {
			if (timer.get()>=targetTime) {
				timer.stop(); //Don't need the timer running anymore
				return true;
			} else {
				return false;
			}
		} else {
			timer.start();
			timerRunning = true;
			return false;
		}
	}

}

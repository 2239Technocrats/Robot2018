package main.org.usfirst.frc.team2239.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class MiniBotDrive extends RobotDrive {
	//Just a comment to show github //TODO delete
    public static final double TOP_SPEED = 1;

    public MiniBotDrive(int leftMotorChannel, int rightMotorChannel) {
        super(new VictorSP(leftMotorChannel), new VictorSP(rightMotorChannel));
    }

    public MiniBotDrive(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor) {
        super(new VictorSP(frontLeftMotor), new VictorSP(rearLeftMotor),
                new VictorSP(frontRightMotor), new VictorSP(rearRightMotor)); //calls the RobotDrive constructor
    }

    public double[] getMotorValues() {
        return new double[] {super.m_rearLeftMotor.get(), -super.m_rearRightMotor.get()};
    }

    public void accelerateTo(double left, double right) {
        double[] speed = getMotorValues();
        tankDrive(accelerateHelper(speed[0], left), accelerateHelper(speed[1], right));
    }

    private double accelerateHelper(double from, double to) {
        if(from < to) {
            return Math.max(to, from - .005);
        } else {
            return Math.min(to, from + .005);
        }
    }

    @Override
    public void tankDrive(GenericHID leftStick, GenericHID rightStick) {
        if (leftStick == null || rightStick == null) {
            throw new NullPointerException("Null HID provided");
        }

        double left = -leftStick.getY();
        double right = -rightStick.getY();

        //rightStick is straight, controller is full speed
        //if the controller is triggered then it will run at full speed

        //straight
        /*
         * This is an artifact from two years ago for smoother control that I don't think works with the new
         * 2017 FRC GenericHID library
         */
        /*
        if (rightStick.getTrigger()) {
            double speed = (left+right)/2.0;
            if (leftStick.getTrigger()) {
                tankDrive(speed, speed);
            } else {
                tankDrive(speed*TOP_SPEED, speed*TOP_SPEED);
            }
        } else {
            if (leftStick.getTrigger()) {
                tankDrive(left, right);
            } else {
                tankDrive(left*TOP_SPEED, right*TOP_SPEED);
            }
        }
        */
    }

    @Override
    public void tankDrive(double left, double right) {
        tankDrive(left, right, true);
    }

    @Override
    public void tankDrive(double left, double right, boolean squared) {
        super.tankDrive(left, right, squared);
    }
}

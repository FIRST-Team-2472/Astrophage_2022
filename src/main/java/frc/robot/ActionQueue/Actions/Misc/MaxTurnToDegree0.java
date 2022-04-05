package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class MaxTurnToDegree0 implements Actionable{
    boolean clockwise;
    
    public MaxTurnToDegree0() {
        if (Robot.imu.getCurrentXAngle() > 0) {
            clockwise = true;
        }
        else {
            clockwise = false;
        }
        
    }

    public void startAction() {
        if (clockwise) 
            Robot.drive.tankDrivePower(.1, -.1);
        else
            Robot.drive.tankDrivePower(-.1, .1);
    }
	
	public void periodic() {

    }
	
	public void endAction() {
        Robot.drive.tankDrivePower(0, 0);
    }

	public boolean isFinished() {
        return Math.abs(Robot.imu.getCurrentXAngle()) <= 4;

    }
}

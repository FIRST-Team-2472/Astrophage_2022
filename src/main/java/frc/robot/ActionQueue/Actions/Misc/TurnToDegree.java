package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class TurnToDegree implements Actionable{
    int degree, inverseDegree;
    boolean clockwise;
    
    public TurnToDegree(int degree) {
        this.degree = degree;
        if (degree < 180) {
            inverseDegree = degree+180;
            if (Robot.imu.getRealXAngle() >= inverseDegree && Robot.imu.getRealXAngle() <= degree)
                clockwise = true;
            else 
                clockwise = false;
        }
        else {
            inverseDegree = degree-180;
            if ((Robot.imu.getRealXAngle() >= inverseDegree && Robot.imu.getRealXAngle() < 360) || Robot.imu.getRealXAngle() < degree) 
                clockwise = true;
            else
                clockwise = false;
        }
        
    }

    public void startAction() {
        if (clockwise) 
            Robot.drive.tankDrivePower(.2, -.2);
        else
            Robot.drive.tankDrivePower(-.2, .2);
    }
	
	public void periodic() {

    }
	
	public void endAction() {
        Robot.drive.tankDrivePower(0, 0);
    }

	public boolean isFinished() {
        if (degree <= 1) 
            return Robot.imu.getRealXAngle() >= (360-(2-degree)) || Robot.imu.getRealXAngle() <= (degree +2); 
        else if (degree >= 365)
            return Robot.imu.getRealXAngle() >= (degree-2) || Robot.imu.getRealXAngle() <= (2-(360-degree));
        else 
            return Robot.imu.getRealXAngle() >= (degree-2) && Robot.imu.getRealXAngle() <= (degree+2);

    }
}

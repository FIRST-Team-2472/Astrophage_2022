package frc.robot.ActionQueue.Actions.Misc;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;


public class OldTurn implements Actionable {
    private double angle;

    
    public OldTurn(double Angle){
        angle = Angle;
    }
    

    @Override
    public void startAction() {
        
    }

    @Override
    public void periodic() {
        if (angle-Robot.imu.getCurrentXAngle() > 0) Robot.drive.tankDrivePower(.2, -.2);
        if (angle-Robot.imu.getCurrentXAngle() < 0) Robot.drive.tankDrivePower(-.2, .2);
    }

    @Override
    public void endAction() {
        Robot.drive.tankDrivePower(0, 0);
    }

    @Override
    public boolean isFinished() {
		return Math.abs(angle-Robot.imu.getCurrentXAngle()) < 2.0;
    }
}
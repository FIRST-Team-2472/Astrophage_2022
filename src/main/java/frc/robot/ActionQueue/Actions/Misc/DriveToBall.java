package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Robot;

public class DriveToBall implements Actionable {
    double limelightCorrection = 0.02;
    double distance = Robot.limelight.get_distance_in();

    @Override
    public void startAction() 
    {
        
    }

    @Override
    public void periodic() 
    {
        Robot.drive.arcadeDrivePower(0.5, -1 * (limelightCorrection * Robot.limelight.targetXAngleFromCenter()));
        Robot.intake.runConveyorPower(0.5);
    }

    @Override
    public void endAction() 
    {
        Robot.drive.arcadeDrivePower(0, 0);
    }

    @Override
    public boolean isFinished()
    {
        if (Robot.limelight.isTargetSpotted() == false) return true;
        else return false;
    }

}

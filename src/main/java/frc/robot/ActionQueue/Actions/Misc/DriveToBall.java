package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Robot;

public class DriveToBall implements Actionable {

    double limelightCorrection = 0.02;
    double distance = Robot.limelight.get_distance_in();

    @Override
    public void startAction() 
    {
        //-1 * (limelightkP * Robot.limelight.targetXAngleFromCenter()));
    }

    @Override
    public void periodic() 
    {
        Robot.drive.arcadeDrivePower(.4, (-0.4 * (0.01 * Robot.limelight.targetXAngleFromCenter())));
        Robot.intake.runConveyorPower(0.5);    
    }

    @Override
    public void endAction() 
    {
        Robot.drive.arcadeDrivePower(0, 0);
        Robot.intake.runConveyorPower(0);
    }

    @Override
    public boolean isFinished()
    {
        if (Robot.limelight.get_distance_in() <= 6) return true;
        else return false;
    }

}

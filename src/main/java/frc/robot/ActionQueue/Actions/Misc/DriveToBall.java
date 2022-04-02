package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.ActionQueue.Runners.Actionable;

import javax.lang.model.util.ElementScanner6;

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
        if (Robot.limelight.get_distance_in() <=1)
            Robot.drive.arcadeDrivePower(.4, (-0.4 * (0.01 * Robot.limelight.targetXAngleFromCenter())));
        else Robot.drive.arcadeDrivePower(0, 0);

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
        return Robot.limelight.get_distance_in() <= 1;
    }

}

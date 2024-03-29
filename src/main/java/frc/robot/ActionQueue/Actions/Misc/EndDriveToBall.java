package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class EndDriveToBall extends TimerBase 
{



    public EndDriveToBall(double seconds) 
    {
        super(seconds);
    }

    @Override
    public void startAction() 
    {
        super.startAction();
        Robot.drive.tankDrivePower(0.1, 0.1);
        Robot.intake.runConveyorPower(0.2);
    }

    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
        Robot.drive.tankDrive(0, 0);
        Robot.intake.runConveyorPower(0);
    }

}
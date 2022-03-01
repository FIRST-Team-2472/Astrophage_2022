package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class EndDriveToBall extends TimerBase 
{

    public double speed;

    public EndDriveToBall(double givenSpeed, double seconds) 
    {
        super(seconds);
        speed = givenSpeed;

    }

    @Override
    public void startAction() 
    {
        super.startAction();
        Robot.drive.tankDrivePower(0.2, 0.2);
        Robot.intake.runConveyorPower(0.5);
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
package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class DriveStraightTime extends TimerBase 
{

    public double speed;

    public DriveStraightTime(double givenSpeed, double seconds) 
    {
        super(seconds);
        speed = givenSpeed;
    }

    @Override
    public void startAction() 
    {
        super.startAction();
        Robot.drive.tankDrivePower(speed, speed);
    }

    @Override
    public void periodic() 
    {}

    @Override
    public void endAction() 
    {
        Robot.drive.tankDrive(0, 0);
    }
}
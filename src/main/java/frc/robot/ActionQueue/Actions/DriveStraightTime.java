package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        SmartDashboard.putString("ActionName", "Drive Straight Time");
        Robot.drive.tankDrive(speed, speed);
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
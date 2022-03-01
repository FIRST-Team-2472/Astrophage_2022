package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class DriveStraightBall extends TimerBase {
    public double speed;

    public DriveStraightBall(double givenSpeed, double seconds) 
    {
        super(seconds);
        speed = givenSpeed;
    }

    @Override
    public void startAction() 
    {
        super.startAction();
        Robot.drive.arcadeDrivePower(-speed, 0);
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
    }
}

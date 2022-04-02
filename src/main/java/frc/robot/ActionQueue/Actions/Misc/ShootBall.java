package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class ShootBall extends TimerBase
{

    public ShootBall(double seconds)
    {
        super(seconds);

    }

    @Override
    public void startAction()
    {
        super.startAction();
        Robot.shooter.runFlyWheelVelocity(0.4);
    }

    @Override
    public void periodic()
    {
        if(Robot.shooter.getSpeed() < -50000) Robot.intake.runConveyorPower(.75);
        else Robot.intake.runConveyorPower(0);
    }

    @Override
    public void endAction()
    {
        Robot.intake.runConveyorPower(0);
        Robot.shooter.runFlyWheelPower(0);
    }
}
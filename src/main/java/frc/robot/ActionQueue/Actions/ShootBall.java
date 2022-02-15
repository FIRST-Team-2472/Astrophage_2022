package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        SmartDashboard.putString("ActionName", "Shoot Ball");
        Robot.shooter.runFlyWheelPower(0.5);
    }

    @Override
    public void periodic()
    {
        
    }

    @Override
    public void endAction()
    {
        Robot.intake.runConveyorPower(0);
        Robot.shooter.runFlyWheelPower(0);
    }
}
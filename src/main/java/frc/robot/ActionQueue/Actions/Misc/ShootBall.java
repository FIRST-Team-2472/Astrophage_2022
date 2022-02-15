package frc.robot.ActionQueue.Actions.Misc;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class ShootBall extends TimerBase
{

    public double conveyorSpeed;
    public double shooterSpeed;

    public ShootBall(double seconds)
    {
        super(seconds);
        conveyorSpeed = .25;
        shooterSpeed = .75;
    }

    @Override
    public void startAction()
    {
        super.startAction();
        SmartDashboard.putString("ActionName", "Shoot Ball");
        Robot.intake.runConveyorPower(conveyorSpeed);
        Robot.shooter.runFlyWheelPower(shooterSpeed);
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
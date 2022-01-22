package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class ShootBall extends TimerBase
{

    public double conveyorSpeed;
    public double shooterSpeed;

    public ShootBall(double givenConveyorSpeed, double givenShooterSpeed, double seconds)
    {
        super(seconds);
        conveyorSpeed = givenConveyorSpeed;
        shooterSpeed = givenShooterSpeed;
    }

    public void StartAction()
    {
        super.startAction();
        SmartDashboard.putString("ActionName", "Shoot Ball");
        Robot.intake.runConveyorPower(conveyorSpeed);
        Robot.shooter.runFlyWheelPower(shooterSpeed);
    }

    public void Periodic()
    {}

    public void EndAction()
    {
        Robot.intake.runConveyorPower(0);
        Robot.shooter.runFlyWheelPower(0);
    }

    @Override
    public void periodic() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void endAction() {
        // TODO Auto-generated method stub
        
    }
}
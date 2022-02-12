package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class EndDriveToBall extends TimerBase 
{



    public EndDriveToBall() 
    {
        super(1);

    }

    @Override
    public void startAction() 
    {
        super.startAction();
        SmartDashboard.putString("ActionName", "Drive and Conveyor");
        Robot.drive.tankDrivePower(0.2, 0.2);
    }

    @Override
    public void periodic() 
    {}

    @Override
    public void endAction() 
    {
        Robot.drive.tankDrive(0, 0);
        Robot.intake.runConveyorPower(0);
    }

}
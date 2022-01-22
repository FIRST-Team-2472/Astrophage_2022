package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class DriveAndConveyor extends TimerBase 
{

    public double speed;
    public double conveyorSpeed;
    public double intakeSpeed;

    public DriveAndConveyor(double givenSpeed, double givenConveyorSpeed, double givenIntakeSpeed, double seconds) 
    {
        super(seconds);
        speed = givenSpeed;
        conveyorSpeed = givenConveyorSpeed;
        intakeSpeed = givenIntakeSpeed;
    }

    @Override
    public void startAction() 
    {
        super.startAction();
        SmartDashboard.putString("ActionName", "Drive and Conveyor");
        Robot.drive.tankDrive(speed, speed);
        Robot.intake.runFrontWheels(intakeSpeed);
        Robot.intake.runConveyorPower(conveyorSpeed);
    }

    @Override
    public void periodic() 
    {}

    @Override
    public void endAction() 
    {
        Robot.drive.tankDrive(0, 0);
        Robot.intake.runFrontWheels(0);
        Robot.intake.runConveyorPower(0);
    }

}
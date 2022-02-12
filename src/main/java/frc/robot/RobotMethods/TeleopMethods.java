package frc.robot.RobotMethods;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;



public class TeleopMethods 
{
    
    public void init() 
    {
        //Displays a value to ShuffleBoard
        SmartDashboard.putString("RobotState", "Telop Enabled");
        Robot.drive.zeroEncoders();
    }


    public void drive() 
    {
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY(), Robot.rightJoystick.getX());
    }

    /*public void intake()
    {

        if (Robot.xboxcontroller.getBButton()) Robot.intake.runConveyorPower(.5);
        else Robot.intake.runConveyorPower(0);
    }

    public void shooter()
    {
        if (Robot.xboxcontroller.getXButtonPressed()) Robot.shooter.runFlyWheelPower(.5);
        else Robot.shooter.runFlyWheelPower(0);
    }*/
}

package frc.robot.RobotMethods;

import frc.robot.Robot;



public class TeleopMethods 
{
    
    public void init() 
    {
        Robot.drive.zeroEncoders();
    }


    public void drive() 
    {
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY(), Robot.leftJoystick.getX());
    }



    /*
    public void intake()
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

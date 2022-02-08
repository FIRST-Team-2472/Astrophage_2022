package frc.robot.RobotMethods;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;



public class TeleopMethods 
{
    boolean intakeWheel = false;
    boolean clamp = false;
    
    public void init() 
    {
        //Displays a value to ShuffleBoard
        SmartDashboard.putString("RobotState", "Telop Enabled");
        Robot.drive.zeroEncoders();
    }


    public void drive() 
    {
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*.5, Robot.leftJoystick.getX()*.5);
    }

    public void intake()
    {

        if (Robot.xboxcontroller.getYButtonPressed())
        {
            if (intakeWheel == false) intakeWheel = true;
            else intakeWheel = false;
            
            if (intakeWheel == false) Robot.intake.pushinfrontwheel();
            else Robot.intake.pushoutfrontwheel();
        }



        if (Robot.xboxcontroller.getBButton()) Robot.intake.runConveyorPower(.5);
        else Robot.intake.runConveyorPower(0);
    }

    public void shooter()
    {
        if (Robot.xboxcontroller.getXButtonPressed()) Robot.shooter.runFlyWheelPower(.5);
        else Robot.shooter.runFlyWheelPower(0);
    }

    public void climberclamp()
    {
        
        if (Robot.xboxcontroller.getRightBumperPressed())
        {
            if (clamp == false) clamp = true;
            else clamp = false;
            
            if (clamp == false) Robot.climberClamp.pushInClamps();
            else Robot.climberClamp.pushUpClamps();
        }
    }

}

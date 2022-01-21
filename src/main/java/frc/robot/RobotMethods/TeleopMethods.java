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

        if (Robot.xboxcontroller.getAButton()) Robot.intake.runFrontWheels(.5);
        else Robot.intake.runFrontWheels(0);

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

    public void climberClamp()
    {
        
        if (Robot.xboxcontroller.getRightBumper())
        {
            if (clamp == false) clamp = true;
            else clamp = false;
            
            if (clamp == false) Robot.climberClamp.pushInClamps();
            else Robot.climberClamp.pushUpClamps();
        }
    }
    
    public void climberClaw()
    {
        if (Robot.xboxcontroller.getPOV() == 0) Robot.climberClaw.runclimberClawBoth(.5);
        else if (Robot.xboxcontroller.getPOV() == 180) Robot.climberClaw.runclimberClawBoth(-.5);
        else Robot.climberClaw.runclimberClawBoth(0);
    }

    public void ClimberMove()
    {
        if (Robot.xboxcontroller.getRightTriggerAxis() >= .6) Robot.climberMove.runclimberMoveBoth(.5);
        else if (Robot.xboxcontroller.getLeftTriggerAxis() >= .6) Robot.climberMove.runclimberMoveBoth(-.5);
        else Robot.climberMove.runclimberMoveBoth(0);
    }
}

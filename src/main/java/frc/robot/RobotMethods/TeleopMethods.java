package frc.robot.RobotMethods;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TeleopMethods {
    
    public void init() {
        //Displays a value to ShuffleBoard
        SmartDashboard.putString("RobotState", "Telop Enabled");
        Robot.drive.zeroEncoders();
    }


    public void drive() {
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*.5, Robot.leftJoystick.getX()*.5);
    }

    public void aimForBall() {

        double limelightkP = 0.02;
     
			Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*.5, (-1 * (limelightkP * Robot.limelight.targetXAngleFromCenter())));
            

        
    }
}

    /*public void shooting () {
        if (Robot.xboxcontroller.getXButton()) {
            Robot.shooter.runFlyWheel(1);

        } else {
            Robot.shooter.runFlyWheel(0);
        }
    }*/

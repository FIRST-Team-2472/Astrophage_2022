package frc.robot.RobotMethods;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class TeleopMethods {
    
    public void init() {
        //Displays a value to ShuffleBoard
        SmartDashboard.putString("RobotState", "Telop Enabled");
        Robot.drive.zeroEncoders();
    }


    public void drive() {
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*.5, Robot.leftJoystick.getX()*.5);
    }
    }
}


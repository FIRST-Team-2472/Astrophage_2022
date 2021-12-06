package frc.robot.RobotMethods;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TeleopMethods {
    
    public void init() {
        SmartDashboard.putString("RobotState", "Telop Enabled");
    }

    public void drive() {
        Robot.drive.arcadeDrive(Robot.leftJoystick.getY() * .5, Robot.leftJoystick.getX() * -.5);
    }
}

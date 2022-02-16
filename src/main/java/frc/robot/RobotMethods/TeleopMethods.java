package frc.robot.RobotMethods;

import frc.robot.Robot;



public class TeleopMethods 
{
    
    public void init() {
    }

    //All three of these are for drivers communicating with the subsystems.
    public void drive() {
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY(), Robot.rightJoystick.getX());
    }

    public void climb() {

    }

    public void shoot() {

    }

    public void manualAutoOveride() {

    }

    public void grabBall() {

    }

    public void manualClimb() {

    }
}

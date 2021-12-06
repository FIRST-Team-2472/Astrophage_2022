package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive {

    private TalonSRX rightMaster;
    private TalonSRX rightSlave;
    private TalonSRX leftMaster;
    private TalonSRX leftSlave;

    public Drive(int backRightID, int frontRightID, int backLeftID, int frontLeftID) {
        rightSlave = new TalonSRX(backRightID);
        rightMaster = new TalonSRX(frontRightID);

        leftSlave = new TalonSRX(backLeftID);
        leftMaster = new TalonSRX(frontLeftID);

        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setInverted(false);
        leftMaster.setInverted(true);
    }

    public void arcadeDrive(double y, double x) {
        //y is the y axis of the joystick
        //x is the x axis of the SAME joystick

        if (Math.abs(x) + Math.abs(y) < .75) {
            tankDrive(y - x, y + x);
        } else {
            // limits the motors from ever going over 75% speed
            double betterX = (x/(Math.abs(x)+Math.abs(y)))*.75;
            double betterY = (y/(Math.abs(x)+Math.abs(y)))*.75;
            tankDrive(betterY - betterX, betterY + betterX);
        }
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        runLeft(leftSpeed);
        runRight(rightSpeed);
    }

    public void runLeft(double speed) {
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    public void runRight(double speed) {
        rightMaster.set(ControlMode.PercentOutput, speed);
    }
}
package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {

    private TalonSRX rightMaster;
    private TalonSRX rightSlave;
    private TalonSRX leftMaster;
    private TalonSRX leftSlave;

    public Drive(int backRightID, int frontRightID, int backLeftID, int frontLeftID) {
        rightMaster = new TalonSRX(backRightID);
        rightSlave = new TalonSRX(frontRightID);

        leftMaster = new TalonSRX(frontLeftID);
        leftSlave = new TalonSRX(backLeftID);

        rightMaster.setInverted(false);
        leftMaster.setInverted(true);

        rightSlave.follow(rightMaster);
        rightSlave.setInverted(InvertType.FollowMaster);
        leftSlave.follow(leftMaster);
        leftSlave.setInverted(InvertType.FollowMaster);

        rightMaster.setSensorPhase(true); // correct encoder to motor direction
        // Tell the talon that he has a quad encoder
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0/* PidIndex */, 30/* magic number */);
        rightMaster.setSelectedSensorPosition(0, 0, 30);

        leftMaster.setSensorPhase(true); // correct encoder to motor direction
        // Tell the talon that he has a quad encoder
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
        rightMaster.setSelectedSensorPosition(0, 0, 30);
    }

    public void arcadeDrive(double y, double x) {
        SmartDashboard.putNumber("hi",2);
        // y is the y axis of the joystick
        // x is the x axis of the SAME joystick
        if (Math.abs(x) < .1) x = 0;

        if (Math.abs(x) + Math.abs(y) < .75) {
            tankDrive(y - x, y + x);
        } else {
            // limits the motors from ever going over 75% speed
            double betterX = (x / (Math.abs(x) + Math.abs(y))) * .75;
            double betterY = (y / (Math.abs(x) + Math.abs(y))) * .75;
            tankDrive(betterY - betterX, betterY + betterX);
        }
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        SmartDashboard.putNumber("hi", 1);
        SmartDashboard.putNumber("Left Encoder", getLeftSpeed());
        SmartDashboard.putNumber("Right Encoder", getRightSpeed());

        runLeft(leftSpeed);
        runRight(rightSpeed);
    }

    public void runLeft(double speed) {
        leftMaster.set(ControlMode.Velocity, speed * -6250);
    }

    public void runRight(double speed) {
        rightMaster.set(ControlMode.Velocity, speed * -6250);
    }

    public void zeroEncoders(){
        rightMaster.setSelectedSensorPosition(0);
        leftMaster.setSelectedSensorPosition(0);
    }

    public double getLeftDistance() {
        return leftMaster.getSelectedSensorPosition();
    }

    public double getRightDistance() {
        return rightMaster.getSelectedSensorPosition();
    }

    public double getRightSpeed() {
        return rightMaster.getSelectedSensorVelocity();
    }

    public double getLeftSpeed() {
        return leftMaster.getSelectedSensorVelocity();
    }
    















    public void arcadeDrivePower(double y, double x) {
        //y is the y axis of the joystick
        //x is the x axis of the SAME joystick

        if (Math.abs(x) + Math.abs(y) < .75) {
            tankDrivePower(y + x, y - x);
        } else {
            // limits the motors from ever going over 75% speed
            double betterX = (x/(Math.abs(x)+Math.abs(y)))*.75;
            double betterY = (y/(Math.abs(x)+Math.abs(y)))*.75;
            tankDrivePower(betterY + betterX, betterY - betterX);
        }
    }

    public void tankDrivePower(double leftSpeed, double rightSpeed) {
        runLeftPower(leftSpeed);
        runRightPower(rightSpeed);
    }

    public void runLeftPower(double speed) {
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    public void runRightPower(double speed) {
        rightMaster.set(ControlMode.PercentOutput, speed);
    }

}
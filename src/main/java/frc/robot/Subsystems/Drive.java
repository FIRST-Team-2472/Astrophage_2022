package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
    //Talon Definition: an instruction reader that gives instructions to the stupid motors after reading the instructions from the big instruction reader that reads instructions from the instruction manual written by the instruction man
    private TalonFX rightMaster;
    private TalonFX rightSlave;
    private TalonFX leftMaster;
    private TalonFX leftSlave;

    //Assigns IDs talons to hand instructions to dum kopf motors
    public Drive(int backRightID, int frontRightID, int backLeftID, int frontLeftID) {
        rightMaster = new TalonFX(backRightID);
        rightSlave = new TalonFX(frontRightID);

        leftMaster = new TalonFX(frontLeftID);
        leftSlave = new TalonFX(backLeftID);

        rightMaster.setInverted(false);
        leftMaster.setInverted(true);

        rightSlave.follow(rightMaster);
        rightSlave.setInverted(InvertType.FollowMaster);
        //Slaves loyally mimic master's movements, including inversion
        leftSlave.follow(leftMaster);
        leftSlave.setInverted(InvertType.FollowMaster);

        leftMaster.config_kP(0, 0.05);
        leftMaster.config_kI(0, 0);
        leftMaster.config_kD(0, 0.05);
        leftMaster.config_kF(0, -.19);

        rightMaster.config_kP(0, 0.05);
        rightMaster.config_kI(0, 0);
        rightMaster.config_kD(0, 0.05);
        rightMaster.config_kF(0, -.19);
    

        //encoders
        
        rightMaster.setSensorPhase(true);  // correct encoder to motor direction
     
        // Tell the talon that he has a quad encoder
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
       
        // Set minimum output (closed loop)  to 0 for now
        rightMaster.configNominalOutputForward(0, 30);
        rightMaster.configNominalOutputReverse(0, 30);
        
        // Set maximum forward and backward to full speed
        rightMaster.configPeakOutputForward(1, 30);
        rightMaster.configPeakOutputReverse(-1, 30);
    
        // Motion magic cruise (max speed) is 100 counts per 100 ms
        rightMaster.configMotionCruiseVelocity(500, 30);
    
        // Motion magic acceleration is 50 counts
        rightMaster.configMotionAcceleration(100, 30);
    
        // Zero the sensor once on robot boot up 
        rightMaster.setSelectedSensorPosition(0, 0, 30);

    //other side

            leftMaster.setSensorPhase(true);  // correct encoder to motor direction

            // Tell the talon that he has a quad encoder
            leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
           
            // Set minimum output (closed loop)  to 0 for now
            leftMaster.configNominalOutputForward(0, 30);
            leftMaster.configNominalOutputReverse(0, 30);
            
            // Set maximum forward and backward to full speed
            leftMaster.configPeakOutputForward(1, 30);
            leftMaster.configPeakOutputReverse(-1, 30);
        
            // Motion magic cruise (max speed) is 100 counts per 100 ms
            leftMaster.configMotionCruiseVelocity(500, 30);
        
            // Motion magic acceleration is 50 counts
            leftMaster.configMotionAcceleration(100, 30);
        
            // Zero the sensor once on robot boot up 
            leftMaster.setSelectedSensorPosition(0, 0, 30);
        
        
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

    public void setLeftTarget(double feet) {
        leftMaster.set(ControlMode.MotionMagic, feet * 1000);
    }

    public void setRightTarget(double feet) {
        leftMaster.set(ControlMode.MotionMagic, feet * 1000);
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













    //power drive hopefully not have to use

    //Better tankDrive used exclussively with joystick(s)
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

    //Runs speed methods base upon two values
    public void tankDrivePower(double leftSpeed, double rightSpeed) {
        runLeftPower(leftSpeed);
        runRightPower(rightSpeed);
    }

    //Commands leftMaster's speed with a percentage
    public void runLeftPower(double speed) {
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    //Commands rightMaster's speed with a percentage
    public void runRightPower(double speed) {
        rightMaster.set(ControlMode.PercentOutput, speed);
    }
    
}
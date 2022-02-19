package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


public class Drive {
    // Talon Definition: an instruction reader that gives instructions to the stupid
    // motors after reading the instructions from the big instruction reader that
    // reads instructions from the instruction manual written by the instruction man
    private TalonFX rightMaster;
    private TalonFX rightSlave;
    private TalonFX leftMaster;
    private TalonFX leftSlave;

    // TODO find speical number
    private double countToFeet = 1;
    private double KP = 0, KF = 0, KI = 0;

    // Assigns IDs talons to hand instructions to dum kopf motors
    public Drive(int backRightID, int frontRightID, int backLeftID, int frontLeftID) {
        rightMaster = new TalonFX(backRightID);
        rightSlave = new TalonFX(frontRightID);

        leftMaster = new TalonFX(frontLeftID);
        leftSlave = new TalonFX(backLeftID);

        rightMaster.setInverted(false);
        leftMaster.setInverted(true);

        rightSlave.follow(rightMaster);
        rightSlave.setInverted(InvertType.FollowMaster);
        // Slaves loyally mimic master's movements, including inversion
        leftSlave.follow(leftMaster);
        leftSlave.setInverted(InvertType.FollowMaster);

        setUpMotionMagicFX(leftMaster, KF, KP, KI);
        setUpMotionMagicFX(rightMaster, KF, KP, KI);

    }

    public void arcadeDrive(double y, double x) {
        // y is the y axis of the joystick
        // x is the x axis of the SAME joystick
        if (Math.abs(x) < .1)
            x = 0;

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
        runLeft(leftSpeed);
        runRight(rightSpeed);
    }

    //Moves the motors (using motion magic) the amount of distance left until target is reached.
    public void driveFeet(double feet) {
        setLeftTarget(feet / countToFeet);
        setRightTarget(feet / countToFeet);
    }

    //Tells the master left motor how fast it needs to move.
    public void runLeft(double speed) {
        leftMaster.set(ControlMode.Velocity, speed * 6250);
    }

    //Tells the master right motor how fast it needs to move.
    public void runRight(double speed) {
        rightMaster.set(ControlMode.Velocity, speed * 6250);
    }

    //Moves the master left motor how much distance is left until target is reached.
    public void setLeftTarget(double feet) {
        leftMaster.set(ControlMode.MotionMagic, feet / countToFeet);
    }

    //Moves the master left motor how much distance is left until target is reached.
    public void setRightTarget(double feet) {
        leftMaster.set(ControlMode.MotionMagic, feet / countToFeet);
    }

    //Resets the distance needed to travel to zero.
    public void zeroEncoders() {
        rightMaster.setSelectedSensorPosition(0);
        leftMaster.setSelectedSensorPosition(0);
    }

    //Returns how much distance (in feet) the left encoder has moved.
    public double getLeftFeet() {
        return leftMaster.getSelectedSensorPosition() / countToFeet;
    }

    //Returns how much distance (in feet) the right encoder has moved.
    public double getRightFeet() {
        return rightMaster.getSelectedSensorPosition() / countToFeet;
    }

    //Returns the right master motor's speed.
    public double getRightSpeed() {
        return rightMaster.getSelectedSensorVelocity();
    }

    //Returns the left master motor's speed.
    public double getLeftSpeed() {
        return leftMaster.getSelectedSensorVelocity();
    }


    private void setUpMotionMagicFX(TalonFX motor, double KF, double KP, double KI) {
        motor.configFactoryDefault();

        // kP formula is (x/1023)/4096
        motor.config_kP(0, KP);
        motor.config_kI(0, KI);
        motor.config_kF(0, KF);

        // D stands for don't touch
        motor.config_kD(0, 0);

        motor.setSensorPhase(true); // correct encoder to motor direction

        // Set minimum output (closed loop) to 0 for now
        motor.configNominalOutputForward(0, 30);
        motor.configNominalOutputReverse(0, 30);

        // Set maximum forward and backward to full speed
        motor.configPeakOutputForward(1, 30);
        motor.configPeakOutputReverse(-1, 30);

        // Motion magic cruise (max speed) is 100 counts per 100 ms
        motor.configMotionCruiseVelocity(2000, 30);

        // Motion magic acceleration is 50 counts
        motor.configMotionAcceleration(2000, 30);

        // Zero the sensor once on robot boot up
        motor.setSelectedSensorPosition(0, 0, 30);
    }

    public double leftDriveDistance() {
        return leftMaster.getSelectedSensorPosition() * countToFeet;
    }

    public double rightDriveDistance() {
        return rightMaster.getSelectedSensorPosition() * countToFeet;
    }




    
    // power drive hopefully not have to use

    // Better tankDrive used exclussively with joystick(s)
    public void arcadeDrivePower(double y, double x) {
        // y is the y axis of the joystick
        // x is the x axis of the SAME joystick

        if (Math.abs(x) + Math.abs(y) < .75) {
            tankDrivePower(y + x, y - x);
        } else {
            // limits the motors from ever going over 75% speed
            double betterX = (x / (Math.abs(x) + Math.abs(y))) * .75;
            double betterY = (y / (Math.abs(x) + Math.abs(y))) * .75;
            tankDrivePower(betterY + betterX, betterY - betterX);
        }
    }

    // Runs speed methods base upon two values
    public void tankDrivePower(double leftSpeed, double rightSpeed) {
        runLeftPower(leftSpeed);
        runRightPower(rightSpeed);
    }

    // Commands leftMaster's speed with a percentage
    public void runLeftPower(double speed) {
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    // Commands rightMaster's speed with a percentage
    public void runRightPower(double speed) {
        rightMaster.set(ControlMode.PercentOutput, speed);
    }

}
package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
    //Talon Definition: an instruction reader that gives instructions to the stupid motors after reading the instructions from the big instruction reader that reads instructions from the instruction manual written by the instruction man
    private TalonSRX rightMaster;
    private TalonSRX rightSlave;
    private TalonSRX leftMaster;
    private TalonSRX leftSlave;

    //Assigns IDs talons to hand instructions to dum kopf motors
    public Drive(int backRightID, int frontRightID, int backLeftID, int frontLeftID) {
        rightMaster = new TalonSRX(backRightID);
        rightSlave = new TalonSRX(frontRightID);

        leftMaster = new TalonSRX(frontLeftID);
        leftSlave = new TalonSRX(backLeftID);

        //Talon is not dumb, doesn't need correcting
        rightMaster.setInverted(false);
        //Talon is "special", needed correcting
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
            rightMaster.configMotionCruiseVelocity(3000, 30);
            rightMaster.configMotionCruiseVelocity(3000, 30);
    
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

                leftMaster.configMotionCruiseVelocity(3000, 30);

                leftMaster.configMotionCruiseVelocity(3000, 30);
        
            // Motion magic acceleration is 50 counts
                leftMaster.configMotionAcceleration(100, 30);
        
                // Zero the sensor once on robot boot up 
                leftMaster.setSelectedSensorPosition(0, 0, 30);
        
        
                //setupMotionMagic(0, 0, 0, 0, 500, 100);
    }


    public void arcadeDrive(double y, double x) {
        SmartDashboard.putNumber("hi",2);
        // y is the y axis of the joystick
        // x is the x axis of the SAME joystick
        if (Math.abs(x) < .1) x = 0;

        if (Math.abs(x) + Math.abs(y) < .75) {
            tankDrive(y + x, y - x);
        } else {
            // limits the motors from ever going over 75% speed
            double betterX = (x / (Math.abs(x) + Math.abs(y))) * .75;
            double betterY = (y / (Math.abs(x) + Math.abs(y))) * .75;
            tankDrive(betterY + betterX, betterY - betterX);
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

    public void getLeftMagicNumber(){
        magicNumber(leftMaster);

    }

   public void getRightMagicNumber(){
        magicNumber(rightMaster);
        
    }















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

    public void magicNumber(TalonSRX Freddy){
        double suggestKF = 0;
        double velocity;
        double error;
        int  waitcount=0;
        int AState=0;

        if (waitcount > 0)
        {
          waitcount--;
          return;
        }
    
        switch (AState)
        {
          case 0:
    
            Freddy.set(ControlMode.PercentOutput, 0 );   // Stop
            break;
    
          case 1:    
            // Determine kF - find nominal speed
            Freddy.set(ControlMode.PercentOutput, .75 );   // Set speed to .75 forward
            SmartDashboard.putNumber("Joystick", .75);
            waitcount = 50;
            AState = 2;
            System.out.println("State 1");
            break;
    
          case 2:
            waitcount = -1;
            velocity = Freddy.getSelectedSensorVelocity();
    
            // If sensor polarity is backward, notify
            //    There is nothing else we can do
            if (velocity < 0)
            {
              System.out.println("Sensor polarity is reversed");
              System.out.println("Reverse parameter in Freddy.setSensorPhase(); statement");
              AState = 0;   // Now quit
              break;
            }
    
            // Sensor polarity is correct - calculate suggested KF
    
            SmartDashboard.putNumber("Sensor Vel:", velocity);
            suggestKF = .75 * 1023 / Freddy.getSelectedSensorVelocity();
            System.out.print("Suggested kF: " );
            System.out.println(suggestKF);
            Freddy.set(ControlMode.PercentOutput, 0 );   // Set speed to .75 forward
            Freddy.config_kF(0, suggestKF, 30);
            SmartDashboard.putNumber("KF", 0);
            waitcount = 50;
    
            //  Now, go into closed loop mode with the new KF and try for 75% speed again.
            Freddy.set(ControlMode.Velocity, velocity);
    
            AState = 3;
            break;
    
            // We have now gone about 1 second trying for 75% speed closed loop
            //   Catch the error and suggest a new KP
    
          case 3:
            error = Freddy.getClosedLoopError(0);
            System.out.print("Closed loop error (KP = 0): " );
            System.out.println(error);
            AState = 4;
            break;
    
          case 4:
            
            
    
          default:
            System.out.print("Unsupported case: ");
            System.out.println(AState);
            AState = 0;
    
            break;
    

        }
      
    }
    
}
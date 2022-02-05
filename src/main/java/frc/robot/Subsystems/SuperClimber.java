package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class SuperClimber {
  private TalonFX extendo1;
  private TalonFX extendo2;

  private TalonSRX rotato1;
  private TalonSRX rotato2;

  //TODO need to find a special number
  private final double encoderToFeet = 1000;
  private final double encoderToAngle = 1001;

  DigitalInput barStopperL;

  public SuperClimber(int extendo1ID, int extendo2ID, int rotato1ID, int rotato2ID, 
      int barStopperLID, int barStopperRID, int rotationLimitLID, int rotationLimitRID, int clawLimitLID, int clawLimitRID) {
    extendo1 = new TalonFX(extendo1ID);
    extendo2 = new TalonFX(extendo2ID);

    rotato1 = new TalonSRX(rotato1ID);
    rotato2 = new TalonSRX(rotato2ID);

    //limit Switches
    barStopperL = new DigitalInput(barStopperLID);

    // encoders
    extendo1.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    extendo1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    extendo1.configNominalOutputForward(0, 30);
    extendo1.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    extendo1.configPeakOutputForward(1, 30);
    extendo1.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    extendo1.configMotionCruiseVelocity(500, 30);
    extendo1.configMotionCruiseVelocity(3000, 30);
    extendo1.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    extendo1.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    extendo1.setSelectedSensorPosition(0, 0, 30);

    /* the other one */

    extendo2.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    extendo2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    extendo2.configNominalOutputForward(0, 30);
    extendo2.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    extendo2.configPeakOutputForward(1, 30);
    extendo2.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    extendo2.configMotionCruiseVelocity(500, 30);
    extendo2.configMotionCruiseVelocity(3000, 30);
    extendo2.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    extendo2.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    extendo2.setSelectedSensorPosition(0, 0, 30);



    //set up rotato encoders

    rotato1.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    rotato1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    rotato1.configNominalOutputForward(0, 30);
    rotato1.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    rotato1.configPeakOutputForward(1, 30);
    rotato1.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    rotato1.configMotionCruiseVelocity(500, 30);
    rotato1.configMotionCruiseVelocity(3000, 30);
    rotato1.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    rotato1.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    rotato1.setSelectedSensorPosition(0, 0, 30);

    
    /* the other one */

    rotato2.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    rotato2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    rotato2.configNominalOutputForward(0, 30);
    rotato2.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    rotato2.configPeakOutputForward(1, 30);
    rotato2.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    rotato2.configMotionCruiseVelocity(500, 30);
    rotato2.configMotionCruiseVelocity(3000, 30);
    rotato2.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    rotato2.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    rotato2.setSelectedSensorPosition(0, 0, 30);
  }


  
  //All this jazz just runs the pistons which extend the claws on the moving arm.
  public void runExtendo1(double speed) {
    extendo1.set(ControlMode.Velocity, speed);
  }

  public void runExtendo2(double speed) {
    extendo2.set(ControlMode.Velocity, speed);
  }

  public void runBothExtendo(double speed) {
    runExtendo1(speed);
    runExtendo2(speed);
  }

  public void runRotato1(double speed) {
    rotato1.set(ControlMode.Velocity, speed);
  }

  public void runRotato2(double speed) {
    rotato2.set(ControlMode.Velocity, speed);
  }

  public void runBothRotato(double speed) {
    runRotato1(speed);
    runRotato2(speed);
  }

  public double getExtendo1Height() {
    return extendo1.getSelectedSensorPosition() * encoderToFeet;
  }

  public double getExtendo2Height() {
    return extendo2.getSelectedSensorPosition() * encoderToFeet;
  }    

  public double getRotato1Angle() {
    return rotato1.getSelectedSensorPosition() * encoderToAngle;
  }

  public double getRotato2Angle() {
    return rotato2.getSelectedSensorPosition() * encoderToAngle;
  }

  public boolean isTouchingBar() {
    return barStopperL.get();
  }
}
package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class SuperClimber {
  private TalonFX extendoL;
  private TalonFX extendoR;

  private TalonSRX rotatoL;
  private TalonSRX rotatoR;

  //TODO need to find a special number
  private final double encoderToFeet = 1000;
  private final double encoderToAngle = 1001;

  private DigitalInput barStopperL, barStopperR;

  public SuperClimber(int extendo1ID, int extendo2ID, int rotato1ID, int rotato2ID, 
      int barStopperLID, int barStopperRID, int rotationLimitLID, int rotationLimitRID) {
    extendoL = new TalonFX(extendo1ID);
    extendoR = new TalonFX(extendo2ID);

    rotatoL = new TalonSRX(rotato1ID);
    rotatoR = new TalonSRX(rotato2ID);

    //limit Switches
    barStopperL = new DigitalInput(barStopperLID);
    barStopperR = new DigitalInput(barStopperRID);
    rotatoL.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, rotationLimitLID);
    rotatoR.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, rotationLimitRID);

    // encoders
    extendoL.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    extendoL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    extendoL.configNominalOutputForward(0, 30);
    extendoL.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    extendoL.configPeakOutputForward(1, 30);
    extendoL.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    extendoL.configMotionCruiseVelocity(500, 30);
    extendoL.configMotionCruiseVelocity(3000, 30);
    extendoL.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    extendoL.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    extendoL.setSelectedSensorPosition(0, 0, 30);

    /* the other one */

    extendoR.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    extendoR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    extendoR.configNominalOutputForward(0, 30);
    extendoR.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    extendoR.configPeakOutputForward(1, 30);
    extendoR.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    extendoR.configMotionCruiseVelocity(500, 30);
    extendoR.configMotionCruiseVelocity(3000, 30);
    extendoR.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    extendoR.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    extendoR.setSelectedSensorPosition(0, 0, 30);



    //set up rotato encoders

    rotatoL.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    rotatoL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    rotatoL.configNominalOutputForward(0, 30);
    rotatoL.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    rotatoL.configPeakOutputForward(1, 30);
    rotatoL.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    rotatoL.configMotionCruiseVelocity(500, 30);
    rotatoL.configMotionCruiseVelocity(3000, 30);
    rotatoL.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    rotatoL.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    rotatoL.setSelectedSensorPosition(0, 0, 30);

    
    /* the other one */

    rotatoR.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    rotatoR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    rotatoR.configNominalOutputForward(0, 30);
    rotatoR.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    rotatoR.configPeakOutputForward(1, 30);
    rotatoR.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    rotatoR.configMotionCruiseVelocity(500, 30);
    rotatoR.configMotionCruiseVelocity(3000, 30);
    rotatoR.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    rotatoR.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    rotatoR.setSelectedSensorPosition(0, 0, 30);
  }


  
  //All this jazz just runs the pistons which extend the claws on the moving arm.
  public void runExtendo1(double speed) {
    extendoL.set(ControlMode.Velocity, speed);
  }

  public void runExtendo2(double speed) {
    extendoR.set(ControlMode.Velocity, speed);
  }

  public void runBothExtendo(double speed) {
    runExtendo1(speed);
    runExtendo2(speed);
  }

  public void runRotato1(double speed) {
    rotatoL.set(ControlMode.Velocity, speed);
  }

  public void runRotato2(double speed) {
    rotatoR.set(ControlMode.Velocity, speed);
  }

  public void runBothRotato(double speed) {
    runRotato1(speed);
    runRotato2(speed);
  }

  public double getExtendo1Height() {
    return extendoL.getSelectedSensorPosition() * encoderToFeet;
  }

  public double getExtendo2Height() {
    return extendoR.getSelectedSensorPosition() * encoderToFeet;
  }    

  public double getRotato1Angle() {
    return rotatoL.getSelectedSensorPosition() * encoderToAngle;
  }

  public double getRotato2Angle() {
    return rotatoR.getSelectedSensorPosition() * encoderToAngle;
  }

  public boolean isTouchingBarLeft() {
    return barStopperL.get();
  }

  public boolean isTouchingBarRight() {
    return barStopperR.get();
  }
}
package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SuperClimber {
  private TalonFX extenderL;
  private TalonFX extenderR;

  private TalonSRX rotationL;
  private TalonSRX rotationR;

  // TODO need to find a special number
  private final double encoderToFeet = 300000;
  public final double encoderToDegrees = 1330000;
  private final double rotationLimit = -10;
  private final double extenderLimit = 2;
  private final double KF = 0, KP = 0, KI = 0;


  public SuperClimber(int extenderLID, int extenderRID, int rotationLID, int rotationRID) {
    extenderL = new TalonFX(extenderLID);
    extenderR = new TalonFX(extenderRID);

    rotationL = new TalonSRX(rotationLID);
    rotationR = new TalonSRX(rotationRID);

    setUpMotionMagicFX(extenderL, KF, KP, KI);
    setUpMotionMagicFX(extenderR, KF, KP, KI);
    setUpMotionMagicSRX(rotationL, KF, KP, KI);
    setUpMotionMagicSRX(rotationR, KF, KP, KI);

    extenderL.setInverted(true);
    extenderR.setInverted(true);
    rotationL.setInverted(true);
    rotationR.setInverted(true);

    //setupSoftLimits();
    // limit Switches
    rotationL.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
    rotationR.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);

  }

  public void runTargetExtenderL(double feet) {
    extenderL.set(ControlMode.MotionMagic, feet * encoderToFeet );
  }

  public void runTargetExtenderR(double feet) {
    extenderR.set(ControlMode.MotionMagic, feet * encoderToFeet);
  }

  public void runBothExtendersTarget(double feet) {
    runTargetExtenderL(feet);
    runTargetExtenderR(feet);
  }

  public void runTargetRotationL(double angle) {
    rotationL.set(ControlMode.MotionMagic, angle * encoderToDegrees);
  }

  public void runTargetRotationR(double angle) {
    rotationR.set(ControlMode.MotionMagic, angle * encoderToDegrees);
  }

  public void runBothRotationsTarget(double angle) {
    runTargetRotationL(angle);
    runTargetRotationR(angle);
  }
  
  //All this jazz just runs the pistons which extend the claws on the moving arm.
  public void runExtenderL(double speed) {
    extenderL.set(ControlMode.Velocity, speed);
  }

  public void runExtenderR(double speed) {
    extenderR.set(ControlMode.Velocity, speed);
  }

  public void runBothExtenders(double speed) {
    runExtenderL(speed);
    runExtenderR(speed);
  }

  public void runRotationL(double speed) {
    rotationL.set(ControlMode.Velocity, speed);
  }

  public void runRotationR(double speed) {
    rotationR.set(ControlMode.Velocity, speed);
  }

  public void runBothRotations(double speed) {
    runRotationL(speed);
    runRotationR(speed);
  }

  //these methods get the height of the climbers based upon encoder values and a predetermined encoder to foot ratio
  public double getExtenderLHeight() {
    return -extenderL.getSelectedSensorPosition();
  }

  public double getExtenderRHeight() {
    return -extenderR.getSelectedSensorPosition();
  }    


  //these methods get the rotaion (in degrees) of the climbers based upon encoder values and a predetermined encoder to degrees ratio
  public double getRotationLAngle() {
    return rotationL.getSelectedSensorPosition();
  }

  public double getRotationRAngle() {
    return rotationR.getSelectedSensorPosition();
  }

  // these methods check if the limit switches on the sadle on being pressed
  public boolean isTouchingBar() {
    return isTouchingBarLeft() && isTouchingBarRight();
  }

  public boolean isTouchingBarLeft() {
    if (extenderL.getSensorCollection().isFwdLimitSwitchClosed() != 0)
    return true;
    else return false;
  }

  public boolean isTouchingBarRight() {
    if (extenderR.getSensorCollection().isFwdLimitSwitchClosed() != 0)
    return true;
    else return false;
  }


  
  public boolean isLeftVertical() {
    return rotationL.getSensorCollection().isRevLimitSwitchClosed();
  }

  public boolean isRightVertical() {
    return rotationR.getSensorCollection().isRevLimitSwitchClosed();
  }

  public void zeroRotationEncoders() {
    rotationL.setSelectedSensorPosition(0);

    rotationR.setSelectedSensorPosition(0);

  }

  public void zeroExtenderEncoders() {
    extenderL.setSelectedSensorPosition(0);

    extenderR.setSelectedSensorPosition(0);

  }

  public void setupSoftLimits() {
    rotationL.configForwardSoftLimitEnable(true);
    rotationL.configForwardSoftLimitThreshold(rotationLimit * encoderToDegrees);

    rotationR.configForwardSoftLimitEnable(true);
    rotationR.configForwardSoftLimitThreshold(rotationLimit * encoderToDegrees);

    extenderL.configForwardSoftLimitEnable(true);
    extenderL.configForwardSoftLimitThreshold(extenderLimit * encoderToFeet);

    extenderR.configForwardSoftLimitEnable(true);
    extenderR.configForwardSoftLimitThreshold(extenderLimit * encoderToFeet);
  }





  private void setUpMotionMagicFX(TalonFX motor, double KF, double KP, double KI) {
    motor.configFactoryDefault();

    // kP formula is (x/1023)/4096
    motor.config_kP(0, KP);
    motor.config_kI(0, KI);
    motor.config_kF(0, KF);

    //D stands for don't touch
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

  private void setUpMotionMagicSRX(TalonSRX motor, double KF, double KP, double KI) {
    motor.configFactoryDefault();

    // kP formula is (x/1023)/4096
    motor.config_kP(0, KP);
    motor.config_kI(0, KI);
    motor.config_kF(0, KF);

    //D stands for don't touch
    motor.config_kD(0, 0);
    
    motor.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

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

  public void runExtenderPowerL(double speed) {
    extenderL.set(ControlMode.PercentOutput, speed);
  }

  public void runExtenderPowerR(double speed) {
    extenderR.set(ControlMode.PercentOutput, speed);
  }

  public void runBothExtendersPower(double speedL, double speedR) {
    runExtenderPowerL(speedL *0.4);
    runExtenderPowerR(speedR* 0.4);
  }

  public void runRotationPowerL(double speed) {
    rotationL.set(ControlMode.PercentOutput, speed);
  }

  public void runRotationPowerR(double speed) {
    rotationR.set(ControlMode.PercentOutput, speed);
  }

  public void runBothRotationsPower(double speedL, double speedR) {
    runRotationPowerL(speedL);
    runRotationPowerR(speedR);
  }
}
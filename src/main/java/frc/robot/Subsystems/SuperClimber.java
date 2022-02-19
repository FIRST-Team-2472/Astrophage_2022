package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class SuperClimber {
  private TalonFX extenderL;
  private TalonFX extenderR;

  private TalonSRX rotationL;
  private TalonSRX rotationR;

  // TODO need to find a special number
  private final double encoderToFeet = 1000;
  public final double encoderToDegrees = 1001;
  private final double roatationLimit = 1002;
  private final double KF = 0, KP = 0, KI = 0;
  public double extenderRError = extenderR.getClosedLoopError(0);
  public double extenderLError = extenderL.getClosedLoopError(0);

  //kP value equation is 1/(error^2)
  public double suggestedKP = (((1 / (extenderRError * extenderRError)) * (1 / (extenderLError * extenderLError))) / 2);
  public double suggestedKF = 0;

  private DigitalInput barStopperL, barStopperR;

  public SuperClimber(int extendo1ID, int extendo2ID, int rotato1ID, int rotato2ID, 
      int barStopperLID, int barStopperRID) {
    extenderL = new TalonFX(extendo1ID);
    extenderR = new TalonFX(extendo2ID);

    rotationL = new TalonSRX(rotato1ID);
    rotationR = new TalonSRX(rotato2ID);

    extenderL.setInverted(true);
    extenderR.setInverted(true);
    setUpMotionMagicFX(extenderL, KF, KP, KI);
    setUpMotionMagicFX(extenderR, KF, KP, KI);
    //setUpMotionMagicSRX(rotationL, KF, KP, KI);
    //setUpMotionMagicSRX(rotationR, KF, KP, KI);

    // limit Switches
    //barStopperL = new DigitalInput(barStopperLID);
    //barStopperR = new DigitalInput(barStopperRID);
    //rotationL.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
    //rotationR.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
      
  }

  public void runTargetExtenderL(double feet) {
    extenderL.set(ControlMode.MotionMagic, feet / encoderToFeet );
  }

  public void runTargetExtenderR(double feet) {
    extenderR.set(ControlMode.MotionMagic, feet / encoderToFeet);
  }

  public void runBothExtendersTarget(double feet) {
    runTargetExtenderL(feet);
    runTargetExtenderR(feet);
  }

  public void runTargetRotationL(double angle) {
    rotationL.set(ControlMode.MotionMagic, angle / encoderToDegrees);
  }

  public void runTargetRotationR(double angle) {
    rotationR.set(ControlMode.MotionMagic, angle / encoderToDegrees);
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
    return extenderL.getSelectedSensorPosition() * encoderToFeet;
  }

  public double getExtenderRHeight() {
    return extenderR.getSelectedSensorPosition() * encoderToFeet;
  }    


  //these methods get the rotaion (in degrees) of the climbers based upon encoder values and a predetermined encoder to degrees ratio
  public double getRotationLAngle() {
    return rotationL.getSelectedSensorPosition() * encoderToDegrees;
  }

  public double getRotationRAngle() {
    return rotationR.getSelectedSensorPosition() * encoderToDegrees;
  }

  // these methods check if the limit switches on the sadle on being pressed
  public boolean isTouchingBar() {
    return isTouchingBarLeft() && isTouchingBarRight();
  }

  public boolean isTouchingBarLeft() {
    return barStopperL.get();
  }

  public boolean isTouchingBarRight() {
    return barStopperR.get();
  }


  
  public boolean getRoationLReverseLimit() {
    return rotationL.getSensorCollection().isRevLimitSwitchClosed();
  }

  public boolean getRoationRReverseLimit() {
    return rotationR.getSensorCollection().isRevLimitSwitchClosed();
  }

  public void zeroRotationEncoders() {
    rotationL.setSelectedSensorPosition(0);
    rotationL.configForwardSoftLimitEnable(true);
    rotationL.configForwardSoftLimitThreshold(roatationLimit * encoderToDegrees);

    rotationR.setSelectedSensorPosition(0);
    rotationR.configForwardSoftLimitEnable(true);
    rotationR.configForwardSoftLimitThreshold(roatationLimit * encoderToDegrees);
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

  public void runExtenderPower(double speed) {
    extenderL.set(ControlMode.PercentOutput, speed);
    extenderR.set(ControlMode.PercentOutput, speed);
  }
}
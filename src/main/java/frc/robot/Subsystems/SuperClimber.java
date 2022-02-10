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

  // TODO need to find a special number
  private final double encoderToFeet = 1000;
  private final double encoderToAngle = 1001;
  private final double KF = 0, KP = 0, KI = 0;

  private DigitalInput barStopperL, barStopperR;

  public SuperClimber(int extendo1ID, int extendo2ID, int rotato1ID, int rotato2ID,
      int barStopperLID, int barStopperRID, int rotationLimitLID, int rotationLimitRID) {
    extendoL = new TalonFX(extendo1ID);
    extendoR = new TalonFX(extendo2ID);

    rotatoL = new TalonSRX(rotato1ID);
    rotatoR = new TalonSRX(rotato2ID);

    setUpMotionMagicFX(extendoL, KF, KP, KI);
    setUpMotionMagicFX(extendoR, KF, KP, KI);

    // limit Switches
    barStopperL = new DigitalInput(barStopperLID);
    barStopperR = new DigitalInput(barStopperRID);
    rotatoL.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
        rotationLimitLID);
    rotatoR.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
        rotationLimitRID);


  }

  // All this jazz just runs the pistons which extend the claws on the moving arm.
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

  // these methods get the height of the climbers based upon encoder values and a
  // predetermined encoder to foot ratio
  public double getExtendo1Height() {
    return extendoL.getSelectedSensorPosition() * encoderToFeet;
  }

  public double getExtendo2Height() {
    return extendoR.getSelectedSensorPosition() * encoderToFeet;
  }

  // these methods get the rotaion (in degrees) of the climbers based upon encoder
  // values and a predetermined encoder to degrees ratio
  public double getRotato1Angle() {
    return rotatoL.getSelectedSensorPosition() * encoderToAngle;
  }

  public double getRotato2Angle() {
    return rotatoR.getSelectedSensorPosition() * encoderToAngle;
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

  private void setUpMotionMagicFX(TalonFX motor, double KF, double KP, double KI) {
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
}
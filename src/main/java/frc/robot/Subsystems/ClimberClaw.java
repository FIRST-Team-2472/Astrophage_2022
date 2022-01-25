package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimberClaw {
  private TalonSRX climberClaw_1;
  private TalonSRX climberClaw_2;

  public ClimberClaw(int climberClaw_1ID, int climberClaw_2ID) {
    climberClaw_1 = new TalonSRX(climberClaw_1ID);
    climberClaw_2 = new TalonSRX(climberClaw_2ID);

    // encoders

    climberClaw_1.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    climberClaw_1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    climberClaw_1.configNominalOutputForward(0, 30);
    climberClaw_1.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    climberClaw_1.configPeakOutputForward(1, 30);
    climberClaw_1.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    climberClaw_1.configMotionCruiseVelocity(500, 30);
    climberClaw_1.configMotionCruiseVelocity(3000, 30);
    climberClaw_1.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    climberClaw_1.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    climberClaw_1.setSelectedSensorPosition(0, 0, 30);

    /* the other one */

    climberClaw_2.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    climberClaw_2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    climberClaw_2.configNominalOutputForward(0, 30);
    climberClaw_2.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    climberClaw_2.configPeakOutputForward(1, 30);
    climberClaw_2.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    climberClaw_2.configMotionCruiseVelocity(500, 30);
    climberClaw_2.configMotionCruiseVelocity(3000, 30);
    climberClaw_2.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    climberClaw_2.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    climberClaw_2.setSelectedSensorPosition(0, 0, 30);
  }

  public void runclimberClaw_1(double speed) {
    climberClaw_1.set(ControlMode.Velocity, speed);
  }

  public void runclimberClaw_2(double speed) {
    climberClaw_2.set(ControlMode.Velocity, speed);
  }

  public void runclimberClawBoth(double speed) {
    runclimberClaw_1(speed);
    runclimberClaw_2(speed);
  }
}
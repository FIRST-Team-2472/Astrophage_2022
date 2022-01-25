package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimberMove {
  private TalonSRX climberMove_1;
  private TalonSRX climberMove_2;

  public ClimberMove(int climberMove_1ID, int climberMove_2ID) {
    climberMove_1 = new TalonSRX(climberMove_1ID);
    climberMove_2 = new TalonSRX(climberMove_2ID);

    // encoders

    climberMove_1.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    climberMove_1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    climberMove_1.configNominalOutputForward(0, 30);
    climberMove_1.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    climberMove_1.configPeakOutputForward(1, 30);
    climberMove_1.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    climberMove_1.configMotionCruiseVelocity(500, 30);
    climberMove_1.configMotionCruiseVelocity(3000, 30);
    climberMove_1.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    climberMove_1.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    climberMove_1.setSelectedSensorPosition(0, 0, 30);

    
    /* the other one */

    climberMove_2.setSensorPhase(true); // correct encoder to motor direction

    // Tell the talon that he has a quad encoder
    climberMove_2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    // Set minimum output (closed loop) to 0 for now
    climberMove_2.configNominalOutputForward(0, 30);
    climberMove_2.configNominalOutputReverse(0, 30);

    // Set maximum forward and backward to full speed
    climberMove_2.configPeakOutputForward(1, 30);
    climberMove_2.configPeakOutputReverse(-1, 30);

    // Motion magic cruise (max speed) is 100 counts per 100 ms
    climberMove_2.configMotionCruiseVelocity(500, 30);
    climberMove_2.configMotionCruiseVelocity(3000, 30);
    climberMove_2.configMotionCruiseVelocity(3000, 30);

    // Motion magic acceleration is 50 counts
    climberMove_2.configMotionAcceleration(100, 30);

    // Zero the sensor once on robot boot up
    climberMove_2.setSelectedSensorPosition(0, 0, 30);
  }

  public void runclimberMove_1(double speed) {
    climberMove_1.set(ControlMode.Velocity, speed);
  }

  public void runclimberMove_2(double speed) {
    climberMove_2.set(ControlMode.Velocity, speed);
  }

  public void runclimberMoveBoth(double speed) {
    runclimberMove_1(speed);
    runclimberMove_2(speed);
  }
}
// Hi!

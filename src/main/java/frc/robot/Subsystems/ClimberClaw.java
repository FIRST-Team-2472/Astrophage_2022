package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimberClaw 
{
  private TalonSRX climberClaw_1;
  private TalonSRX climberClaw_2;

  public ClimberClaw (int climberClaw_1ID, int climberClaw_2ID)
  {
    climberClaw_1 = new TalonSRX(climberClaw_1ID);
    climberClaw_2 = new TalonSRX(climberClaw_2ID);
  }
  
  public void runclimberClaw_1(double speed)
  {
    climberClaw_1.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberClaw_2(double speed)
  {
    climberClaw_2.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberClawBoth(double speed)
  {
    climberClaw_1.set(ControlMode.PercentOutput, speed);
    climberClaw_2.set(ControlMode.PercentOutput, speed);
  }
}
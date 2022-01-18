package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimberClaw 
{
  private TalonSRX climberClawEins;
  private TalonSRX climberClawZwei;

  public ClimberClaw (int climberClawEinsID, int climberClawZweiID)
  {
    climberClawEins = new TalonSRX(climberClawEinsID);
    climberClawZwei = new TalonSRX(climberClawZweiID);
  }
  
  public void runclimberClawEins(double speed)
  {
    climberClawEins.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberClawZwei(double speed)
  {
    climberClawZwei.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberClawBoth(double speed)
  {
    climberClawEins.set(ControlMode.PercentOutput, speed);
    climberClawZwei.set(ControlMode.PercentOutput, speed);
  }
}
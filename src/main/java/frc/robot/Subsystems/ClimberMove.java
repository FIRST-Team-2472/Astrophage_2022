package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimberMove 
{
    private TalonSRX climberMoveEins;
    private TalonSRX climberMoveZwei;

    public ClimberMove (int climberMoveEinsID, int climberMoveZweiID)
    {
      climberMoveEins = new TalonSRX(climberMoveEinsID);
      climberMoveZwei = new TalonSRX(climberMoveZweiID);
    }
    
    public void runclimberMoveEins(double speed)
  {
    climberMoveEins.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberMoveZwei(double speed)
  {
    climberMoveZwei.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberMoveBoth(double speed)
  {
    climberMoveEins.set(ControlMode.PercentOutput, speed);
    climberMoveZwei.set(ControlMode.PercentOutput, speed);
  }
}
//Hi!

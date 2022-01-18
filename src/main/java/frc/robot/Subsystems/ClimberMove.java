package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimberMove 
{
    private TalonSRX climberMove_1;
    private TalonSRX climberMove_2;

    public ClimberMove (int climberMove_1ID, int climberMove_2ID)
    {
      climberMove_1 = new TalonSRX(climberMove_1ID);
      climberMove_2 = new TalonSRX(climberMove_2ID);
    }
    
    public void runclimberMove_1(double speed)
  {
    climberMove_1.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberMove_2(double speed)
  {
    climberMove_2.set(ControlMode.PercentOutput, speed);
  }

  public void runclimberMoveBoth(double speed)
  {
    climberMove_1.set(ControlMode.PercentOutput, speed);
    climberMove_2.set(ControlMode.PercentOutput, speed);
  }
}
//Hi!

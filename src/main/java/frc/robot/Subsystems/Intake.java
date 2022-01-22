package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake
{
  private final TalonSRX conveyor;
  private final TalonSRX frontWheels;
  private final DoubleSolenoid frontWheelPush;

  public Intake (int conveyorID, int frontWheelsID, int pcmID, int frontWheelForwardID, int frontWheelBackID)
  {
      conveyor = new TalonSRX(conveyorID);
      frontWheels = new TalonSRX(frontWheelsID);
      //Don't know what PnuematicsModuelType does, but it works.
      frontWheelPush = new DoubleSolenoid(pcmID, PneumaticsModuleType.CTREPCM, frontWheelForwardID, frontWheelBackID);
      conveyor.setInverted(true);
  }

  public void runIntake(double speed) 
  {
      runFrontWheels(-speed);
      runConveyorPower(speed);
  }

  public void runFrontWheels(double speed)
  {
      frontWheels.set(ControlMode.PercentOutput, speed);
  }

  public void runConveyorPower(double speed) 
  {
      conveyor.set(ControlMode.PercentOutput, speed);
  }

  public void pushoutfrontwheel() 
  {
      frontWheelPush.set(Value.kForward);
  }

  public void turnOffFrontWheel()
  {
      frontWheelPush.set(Value.kOff);
  }

  public void pushinfrontwheel() 
  {
      frontWheelPush.set(Value.kReverse);
  }
}
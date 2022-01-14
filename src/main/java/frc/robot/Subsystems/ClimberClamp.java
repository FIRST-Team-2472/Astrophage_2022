package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimberClamp {
    private DoubleSolenoid ClampOne;
    private DoubleSolenoid ClampTwo;
    private DoubleSolenoid Clamp;

    public ClimberClamp (int ClampOneID, int ClampTwoID, int pcmID) 
    {
        //Don't know what PnuematicsModuelType does, but it works.
        Clamp = new DoubleSolenoid(pcmID, PneumaticsModuleType.CTREPCM, ClampOneID, ClampTwoID);
    }

    public void pushUpClamps() 
    {
        pushUpClampOne();
        pushUpClampTwo();
    }

    public void pushInClamps()
    {
        pushInClampOne();
        pushInClampTwo();
    }

    public void turnOffClamps()
    {
        turnOffClampOne();
        turnOffClampTwo();
    }

    public void pushUpClampOne() 
    {
        ClampOne.set(Value.kForward);
    }

    public void turnOffClampOne()
    {
        ClampOne.set(Value.kOff);
    }
  
    public void pushInClampOne() 
    {
        ClampOne.set(Value.kReverse);
    }
    public void pushUpClampTwo() 
    {
        ClampTwo.set(Value.kForward);
    }
    public void turnOffClampTwo()
    {
        ClampTwo.set(Value.kOff);
    }
  
    public void pushInClampTwo() 
    {
        ClampTwo.set(Value.kReverse);
    }
}

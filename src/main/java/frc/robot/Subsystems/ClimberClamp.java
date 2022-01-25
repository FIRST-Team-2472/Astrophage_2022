package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimberClamp {
    private DoubleSolenoid clamp1;
    private DoubleSolenoid clamp2;

    public ClimberClamp (int clamp1ForwardID, int clamp1BackwardID, int clamp2ForwardID, int clamp2BackwardID)
    {
        //Don't know what PnuematicsModuelType does, but it works.
        clamp1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clamp1ForwardID, clamp1BackwardID);
        clamp2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clamp2ForwardID, clamp2BackwardID);
    }

    public void pushUpClamps() 
    {
        pushUpclamp1();
        pushUpclamp2();
    }

    public void pushInClamps()
    {
        pushInclamp1();
        pushInclamp2();
    }

    public void turnOffClamps()
    {
        turnOffclamp1();
        turnOffclamp2();
    }

    //This is for the clamp on the immobile climbing arm at work
    public void pushUpclamp1() 
    {
        clamp1.set(Value.kForward);
    }

    public void turnOffclamp1()
    {
        clamp1.set(Value.kOff);
    }
  
    public void pushInclamp1() 
    {
        clamp1.set(Value.kReverse);
    }

    public void pushUpclamp2() 
    {
        clamp2.set(Value.kForward);
    }
    
    public void turnOffclamp2()
    {
        clamp2.set(Value.kOff);
    }
  
    public void pushInclamp2() 
    {
        clamp2.set(Value.kReverse);
    }
}

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimberClamp {
    private DoubleSolenoid clamp1;
    private DoubleSolenoid clamp2;

    private DigitalInput clawLimitL, clawLimitR;

    public ClimberClamp (int clamp1ForwardID, int clamp1BackwardID, int clamp2ForwardID, int clamp2BackwardID, int clawLimitLID, int clawLimitRID)
    {
        //Don't know what PnuematicsModuelType does, but it works.
        clamp1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clamp1ForwardID, clamp1BackwardID);
        clamp2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clamp2ForwardID, clamp2BackwardID);

        //Limit Switches
        clawLimitL = new DigitalInput(clawLimitLID);
        clawLimitR = new DigitalInput(clawLimitRID);
    }

    public void setClamps() 
    {
        setClamp1();
        setClamp2();
    }

    public void disengageClamps()
    {
        disengageClamp1();
        disengageClamp2();
    }

    public void turnOffClamps()
    {
        turnOffclamp1();
        turnOffclamp2();
    }

    //This is for the clamp on the immobile climbing arm at work
    public void setClamp1() 
    {
        clamp1.set(Value.kForward);
    }

    public void setClamp2()
    {
        clamp1.set(Value.kForward);
    }
  
    public void disengageClamp1() 
    {
        clamp1.set(Value.kReverse);
    }

    public void disengageClamp2() 
    {
        clamp2.set(Value.kReverse);
    }
    
    public void turnOffclamp1() {
        clamp1.set(Value.kOff);
    }
  
    public void turnOffclamp2() {
        clamp2.set(Value.kOff);
    }

    public boolean isFullyClamped() {
        return isClampedL() && isClampedR();
    }

    public boolean isClampedL() {
        return clawLimitL.get();
    }
    
    public boolean isClampedR() {
        return clawLimitR.get();
    }
}

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimberClamp {
    private DoubleSolenoid clampL;
    private DoubleSolenoid clampR;

    private DigitalInput clawLimitL, clawLimitR;

    public ClimberClamp (int clampLForwardID, int clampLBackwardID, int clampRForwardID, int clampRBackwardID, int clawLimitLID, int clawLimitRID)
    {
        //Don't know what PnuematicsModuelType does, but it works.
        clampR = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clampLForwardID, clampLBackwardID);
        clampR = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clampRForwardID, clampRBackwardID);

        //Limit Switches
        clawLimitL = new DigitalInput(clawLimitLID);
        clawLimitR = new DigitalInput(clawLimitRID);
    }

    public void setClamps() 
    {
        setClampR();
        setClampL();
    }

    public void disengageClamps()
    {
        disengageClampR();
        disengageClampL();
    }

    public void turnOffClamps()
    {
        turnOffclampR();
        turnOffclampL();
    }

    //This is for the clamp on the immobile climbing arm at work
    public void setClampL() 
    {
        clampL.set(Value.kForward);
    }

    public void setClampR()
    {
        clampR.set(Value.kForward);
    }
  
    public void disengageClampL() 
    {
        clampL.set(Value.kReverse);
    }

    public void disengageClampR() 
    {
        clampR.set(Value.kReverse);
    }
    
    public void turnOffclampL() {
        clampL.set(Value.kOff);
    }
  
    public void turnOffclampR() {
        clampR.set(Value.kOff);
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

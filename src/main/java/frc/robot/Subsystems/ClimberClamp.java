package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimberClamp {
    private DoubleSolenoid clampL;
    private DoubleSolenoid clampR;

    private DigitalInput clawLimitL, clawLimitR;

    public ClimberClamp (int clampLForwardID, int clampLBackwardID, int clampRForwardID, int clampRBackwardID)
    {
        //Initializes the pistons as, get this, pistons.
        clampL = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clampLForwardID, clampLBackwardID);
        clampR = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, clampRForwardID, clampRBackwardID);

        //Limit Switches
        //clawLimitL = new DigitalInput(clawLimitLID);
        //clawLimitR = new DigitalInput(clawLimitRID);
    }

    //Hooks the hooks.
    public void setClamps() 
    {
        setClampR();
        setClampL();
    }

    //Unhooks the hooks.
    public void disengageClamps()
    {
        disengageClampR();
        disengageClampL();
    }

    //Turns off the pistons for the hooks.
    public void turnOffClamps()
    {
        turnOffclampR();
        turnOffclampL();
    }

    //Hooks the left hook.
    public void setClampL() 
    {
        clampL.set(Value.kForward);
    }

    //Hooks the right hook.
    public void setClampR()
    {
        clampR.set(Value.kForward);
    }
  
    //Unhooks the left hook.
    public void disengageClampL() 
    {
        clampL.set(Value.kReverse);
    }

    //Unhooks the right hook.
    public void disengageClampR() 
    {
        clampR.set(Value.kReverse);
    }
    
    //Turns of the left hook's piston.
    public void turnOffclampL() {
        clampL.set(Value.kOff);
    }
  
    //Turns off the right hook's piston.
    public void turnOffclampR() {
        clampR.set(Value.kOff);
    }

    //Returns if both hooks being hooked.
    public boolean isFullyClamped() {
        return isClampedL() && isClampedR();
    }

    //Returns if the left hook being hooked.
    public boolean isClampedL() {
        return clawLimitL.get();
    }
    
    //Returns if the right hook being hooked.
    public boolean isClampedR() {
        return clawLimitR.get();
    }
}

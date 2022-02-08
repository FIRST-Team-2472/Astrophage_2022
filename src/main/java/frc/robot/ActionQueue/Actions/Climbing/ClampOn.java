package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class ClampOn extends TimerBase{

    public ClampOn(double seconds) {
        super(seconds);
    }
    
    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "clamped");
        Robot.climberClamp.setClamps();
    }



    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
    }


}
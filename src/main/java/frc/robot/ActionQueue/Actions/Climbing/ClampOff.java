package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class ClampOff extends TimerBase{

    public ClampOff() {
        super(.1);
    }
    
    @Override
    public void startAction() 
    {
        Robot.climberClamp.disengageClamps();;
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

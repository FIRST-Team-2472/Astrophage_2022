package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class ClampOn extends TimerBase{

    public ClampOn() {
        super(.1);
    }
    
    @Override
    public void startAction() 
    {
        super.startAction();
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
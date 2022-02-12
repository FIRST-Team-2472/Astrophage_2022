package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.TimerBase;

public class ClampOff extends TimerBase{

    public ClampOff() {
        super(.1);
    }
    
    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "release teh kraken");
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

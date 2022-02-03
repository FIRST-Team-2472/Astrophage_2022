package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ActionQueue.Runners.Actionable;

public class MoveLeadingClimber implements Actionable {

    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Aiming Climber");
    }

    @Override
    public void periodic() 
    {
        
    }

    @Override
    public void endAction() 
    {

    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

}

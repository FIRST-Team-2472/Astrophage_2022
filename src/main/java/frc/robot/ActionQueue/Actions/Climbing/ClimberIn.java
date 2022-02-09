package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class ClimberIn implements Actionable{

    public ClimberIn() {
        //bout to catch me a big fish
    }
    
    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Reeling her in");
        Robot.superClimber.runBothExtendo(-0.3);
    }



    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothExtendo(0);
    }

    @Override
    public boolean isFinished()
    {
        if(Robot.superClimber.isTouchingBar()) return true;
        else return false;
    }

}


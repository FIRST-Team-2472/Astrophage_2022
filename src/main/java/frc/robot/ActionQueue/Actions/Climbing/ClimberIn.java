package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class ClimberIn implements Actionable{

    public ClimberIn() {
        //bout to catch me a big fish
    }
    
    @Override
    public void startAction() 
    {
        Robot.superClimber.runBothExtenders(-0.3);
    }



    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothExtenders(0);
    }

    @Override
    public boolean isFinished()
    {
        if(Robot.superClimber.isTouchingBar()) return true;
        else return false;
    }

}


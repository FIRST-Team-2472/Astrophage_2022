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
        Robot.superClimber.runBothExtendersPower(-0.5, -0.5);
    }



    @Override
    public void periodic() 
    {
        if(Robot.climberClamp.isClampedL()) Robot.superClimber.runExtenderPowerL(0);
        if(Robot.climberClamp.isClampedR()) Robot.superClimber.runExtenderPowerR(0);
    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothExtendersPower(0, 0);
    }

    @Override
    public boolean isFinished()
    {
        if((Robot.climberClamp.isClampedL() && Robot.climberClamp.isClampedR()) || Robot.xboxcontroller.getAButtonPressed()) return true;
        else return false;
    }

}


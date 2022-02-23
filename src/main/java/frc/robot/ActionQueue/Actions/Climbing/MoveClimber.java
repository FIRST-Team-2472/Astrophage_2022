package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class MoveClimber implements Actionable{

    public double feet;
    public boolean upDog;

    public MoveClimber(double feet) {
        this.feet = feet;
        if (feet < Robot.superClimber.getExtenderLHeight()) upDog = true;
        else upDog = false; /*:(*/
    }
    
    @Override
    public void startAction() 
    {
        //TODO need to use motion magic
        if (upDog) Robot.superClimber.runBothExtendersTarget(feet);
        else Robot.superClimber.runBothExtendersTarget(feet);
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
        if (upDog) {
            if (feet <= Robot.superClimber.getExtenderLHeight()) return true;
            else return false;
        }
        else {
            if (feet >= Robot.superClimber.getExtenderLHeight()) return true;
            else return false;
        }
    }

}


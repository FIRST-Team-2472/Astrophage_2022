package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class MoveClimberPower implements Actionable{

    public double distance;
    public boolean upDog;

    public MoveClimberPower(double distance) {
        this.distance = distance;
        if (distance < Robot.superClimber.getExtenderLHeight()) upDog = true;
        else upDog = false; /*:(*/
    }
    
    @Override
    public void startAction() 
    {
        //TODO need to use motion magic
        double bruh = -(Robot.superClimber.getExtenderLHeight() - Robot.superClimber.getExtenderRHeight()) * 0.00001;

        if (upDog) Robot.superClimber.runBothExtendersPower(0.3,0.3+bruh);
        else Robot.superClimber.runBothExtendersPower(-0.3,-0.3+bruh);
    }



    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothExtendersPower(0,0);
    }

    @Override
    public boolean isFinished()
    {
        if (upDog) {
            if (distance <= Robot.superClimber.getExtenderLHeight()) return true;
            else return false;
        }
        else {
            if (distance >= Robot.superClimber.getExtenderLHeight()) return true;
            else return false;
        }
    }

}
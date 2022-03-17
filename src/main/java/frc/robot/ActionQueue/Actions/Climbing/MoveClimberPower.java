package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class MoveClimberPower implements Actionable{

    public double distance;
    public boolean upDog;

    public MoveClimberPower(double feet) {
        distance = feet;
        if (distance > Robot.superClimber.getExtenderLHeight()) upDog = true;
        else upDog = false; /*:(*/
    }
    
    @Override
    public void startAction() 
    {
        double bruh = -(Robot.superClimber.getExtenderLHeight() - Robot.superClimber.getExtenderRHeight()) *6;

        if (upDog) {
            if (distance >= Robot.superClimber.getExtenderRHeight())
                Robot.superClimber.runExtenderPowerR(.6);
            if (distance >= Robot.superClimber.getExtenderLHeight()) 
                Robot.superClimber.runExtenderPowerL(.6+bruh);
        }
        else {
            if (distance <= Robot.superClimber.getExtenderRHeight())
                Robot.superClimber.runExtenderPowerR(-.6);
            if (distance <= Robot.superClimber.getExtenderLHeight()) 
                Robot.superClimber.runExtenderPowerL(-.6+bruh);
        }
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
            if (distance <= Robot.superClimber.getExtenderLHeight() && distance <= Robot.superClimber.getExtenderRHeight()) return true;
            else return false;
        }
        else {
            if (distance >= Robot.superClimber.getExtenderLHeight() && distance >= Robot.superClimber.getExtenderRHeight()) return true;
            else return false;
        }
    }

}
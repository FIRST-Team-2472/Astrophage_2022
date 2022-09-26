package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class MoveClimberPower implements Actionable{

    public double distance;
    public boolean upDog;

    public MoveClimberPower(double inches) {
        distance = inches;
    }
    
    @Override
    public void startAction() {
        if (distance > Robot.superClimber.getExtenderLHeight()) upDog = true;
        else upDog = false; /*:(*/
    }




    @Override
    public void periodic() 
    {
        double correction = (Robot.superClimber.getExtenderLHeight() - Robot.superClimber.getExtenderRHeight()) *.5;

        if (upDog) {
            if (distance >= Robot.superClimber.getExtenderRHeight())
                Robot.superClimber.runExtenderPowerR(.5+correction);
            else Robot.superClimber.runExtenderPowerR(0);
            if (distance >= Robot.superClimber.getExtenderLHeight()) 
                Robot.superClimber.runExtenderPowerL(.5);
            else Robot.superClimber.runExtenderPowerL(0);
        }
        else {
            if (distance <= Robot.superClimber.getExtenderRHeight())
                Robot.superClimber.runExtenderPowerR(-.5+correction);
            else Robot.superClimber.runExtenderPowerR(0);
            if (distance <= Robot.superClimber.getExtenderLHeight()) 
                Robot.superClimber.runExtenderPowerL(-.5);
            else Robot.superClimber.runExtenderPowerL(0);
        }
    
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
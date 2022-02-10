package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class MoveClimber implements Actionable{

    public double feet;
    public boolean upDog;

    public MoveClimber(double ffeet) {
        feet = ffeet;
        if (feet < Robot.superClimber.getExtenderLHeight()) upDog = true;
        else upDog = false; /*:(*/
    }
    
    @Override
    public void startAction() 
    {
        //TODO need to use motion magic
        SmartDashboard.putString("ActionName", "Moving Climber");
        if (upDog) Robot.superClimber.runBothExtenders(0.3);
        else Robot.superClimber.runBothExtenders(-.3);
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


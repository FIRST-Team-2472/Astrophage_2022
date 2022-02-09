package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class AlignClimber implements Actionable{

    public AlignClimber() {
        
        //bout to catch me a big fish
    }
    
    @Override
    public void startAction() 
    {
        //TODO needs motion magic
        SmartDashboard.putString("ActionName", "Straghting the climber");
    }



    @Override
    public void periodic() 
    {
        if (Robot.superClimber.getRotato1Angle() < 0) 
            Robot.superClimber.runRotato1(0.3);
        else if (Robot.superClimber.getRotato1Angle() > 0) 
            Robot.superClimber.runBothRotato(-0.3);
        else if (Math.abs(Robot.superClimber.getRotato1Angle()) < 1)  
            Robot.superClimber.runRotato2(0);
        if (Robot.superClimber.getRotato2Angle() < 0)
            Robot.superClimber.runRotato2(0.3);
        else if (Robot.superClimber.getRotato2Angle() > 0) 
            Robot.superClimber.runRotato2(0.3);
        else if (Math.abs(Robot.superClimber.getRotato2Angle()) < 1)
            Robot.superClimber.runRotato2(0);
    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothExtendo(0);
    }

    @Override
    public boolean isFinished()
    {
        if (Math.abs(Robot.superClimber.getRotato1Angle()) <= 1) return true;
        else return false;
    }

}


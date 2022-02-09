package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class RotateClimber implements Actionable {
    
    public double degrees;

    public RotateClimber(double ddegrees) {
        degrees = ddegrees;
    }
    
    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Twistin' her around");
    }

    @Override
    public void periodic() 
    {
        if (Robot.superClimber.getRotato1Angle() < degrees) 
            Robot.superClimber.runRotato1(0.3);
        else if (Robot.superClimber.getRotato1Angle() > degrees) 
            Robot.superClimber.runBothRotato(-0.3);
        else if (Math.abs(degrees - Robot.superClimber.getRotato1Angle()) < 1)  
            Robot.superClimber.runRotato2(0);
        if (Robot.superClimber.getRotato2Angle() < degrees)
            Robot.superClimber.runRotato2(0.3);
        else if (Robot.superClimber.getRotato2Angle() > degrees) 
            Robot.superClimber.runRotato2(0.3);
        else if (Math.abs(degrees - Robot.superClimber.getRotato2Angle()) < 1)
            Robot.superClimber.runRotato2(0);
    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothRotato(0);
    }

    @Override
    public boolean isFinished()
    {
        if ((Math.abs(degrees - Robot.superClimber.getRotato1Angle()) < 0.1) && (Math.abs(degrees - Robot.superClimber.getRotato2Angle()) < 0.1))
            return true;
        else
            return false;
    }
}


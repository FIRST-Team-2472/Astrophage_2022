package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class RotateClimber implements Actionable {
    
    public double angle;

    public RotateClimber(double angle) {
        this.angle = angle;
    }
    
    @Override
    public void startAction() 
    {
    }

    @Override
    public void periodic() 
    {
        if (Robot.superClimber.getRotationLAngle() < angle) 
            Robot.superClimber.runRotationL(.3);
        else if (Robot.superClimber.getRotationLAngle() > angle) 
            Robot.superClimber.runRotationL(-.3);
        else if (Math.abs(angle - Robot.superClimber.getRotationLAngle()) < 1)  
            Robot.superClimber.runRotationL(0);
        
        if (Robot.superClimber.getRotationRAngle() < angle)
            Robot.superClimber.runRotationR(0.3);
        else if (Robot.superClimber.getRotationRAngle() > angle) 
            Robot.superClimber.runRotationR(0.3);
        else if (Math.abs(angle - Robot.superClimber.getRotationRAngle()) < 1)
            Robot.superClimber.runRotationR(0);
    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothRotations(0);
    }

    @Override
    public boolean isFinished()
    {
        if ((Math.abs(angle - Robot.superClimber.getRotationLAngle()) < 0.1) && (Math.abs(angle - Robot.superClimber.getRotationRAngle()) < 0.1))
            return true;
        else
            return false;
    }
}


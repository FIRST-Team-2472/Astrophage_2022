package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class RotateClimber implements Actionable {
    
    public double angle;
    public boolean forward;

    public RotateClimber(double angle) {
        this.angle = angle;
        if (angle > Robot.superClimber.getRotationRAngle()) forward = true;
        else forward = false;
    }
    
    @Override
    public void startAction() 
    {
    }

    @Override
    public void periodic() 
    {
        double correction = -(Robot.superClimber.getRotationLAngle() - Robot.superClimber.getRotationRAngle()) *0.0000007;

        if (forward) {
            if (angle >= Robot.superClimber.getRotationLAngle())
                Robot.superClimber.runRotationPowerL(.5);
            else 
                Robot.superClimber.runRotationPowerL(0);
            if (angle >= Robot.superClimber.getRotationRAngle()) 
                Robot.superClimber.runRotationPowerR(.5+correction);
            else 
                Robot.superClimber.runRotationPowerR(0);
        }
        else {
            if (angle <= Robot.superClimber.getRotationLAngle())
                Robot.superClimber.runRotationPowerL(-.5);
            else 
                Robot.superClimber.runRotationPowerL(0);
            if (angle <= Robot.superClimber.getRotationRAngle()) 
                Robot.superClimber.runRotationPowerR(-.5+correction);
            else 
                Robot.superClimber.runRotationPowerR(0);
        }
    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothRotations(0);
    }

    @Override
    public boolean isFinished()
    {
        if (forward) {
            if (angle <= Robot.superClimber.getRotationLAngle() && angle <= Robot.superClimber.getRotationRAngle()) return true;
            else return false;
        } else {
            if (angle >= Robot.superClimber.getRotationLAngle() && angle >= Robot.superClimber.getRotationRAngle()) return true;
            else return false;
        }
    }
}


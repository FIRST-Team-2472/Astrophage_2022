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
        if (Robot.superClimber.getRotationLAngle() < degrees) 
            Robot.superClimber.runRotationL(.3);
        else if (Robot.superClimber.getRotationLAngle() > degrees) 
            Robot.superClimber.runRotationL(-.3);
        else if (Math.abs(degrees - Robot.superClimber.getRotationLAngle()) < 1)  
            Robot.superClimber.runRotationL(0);
        
        if (Robot.superClimber.getRotationRAngle() < degrees)
            Robot.superClimber.runRotationR(0.3);
        else if (Robot.superClimber.getRotationRAngle() > degrees) 
            Robot.superClimber.runRotationR(0.3);
        else if (Math.abs(degrees - Robot.superClimber.getRotationRAngle()) < 1)
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
        if ((Math.abs(degrees - Robot.superClimber.getRotationLAngle()) < 0.1) && (Math.abs(degrees - Robot.superClimber.getRotationRAngle()) < 0.1))
            return true;
        else
            return false;
    }
}


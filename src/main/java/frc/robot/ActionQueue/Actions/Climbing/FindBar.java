package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class FindBar implements Actionable {
    
    @Override
    public void startAction() 
    {
        Robot.superClimber.runBothRotationsPower(0.3,0.3);
    }



    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
    Robot.superClimber.runBothRotations(0);
    }

    @Override
    public boolean isFinished()
    {
        if (Math.abs(Robot.imu.getCurrentXAngle()) >= 2) 
            return true;
        else return false;
    }
}

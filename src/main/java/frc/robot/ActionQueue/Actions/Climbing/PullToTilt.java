package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class PullToTilt implements Actionable {
    int angle;

    public PullToTilt() {
        this.angle = 4;
    }

    public PullToTilt(int angle) {
        this.angle = angle;
    }

    @Override
    public void startAction() 
    {
        Robot.superClimber.runBothExtendersPower(-0.5,-0.5);
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
        if (Robot.imu.getCurrentZAngle() <= -Math.abs(angle)) 
            return true;
        else return false;
    }
}

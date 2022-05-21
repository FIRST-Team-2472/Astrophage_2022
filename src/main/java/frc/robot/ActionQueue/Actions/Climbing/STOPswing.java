package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Miscellaneous.Timer;

public class STOPswing implements Actionable {
    //Not currently used
    Timer bruh = new Timer(2);

    @Override
    public void startAction() 
    {
        bruh.reset();
    }



    @Override
    public void periodic() 
    {
        if(Robot.imu.getCurrentZAngle() < -17) bruh.reset();
    }

    @Override
    public void endAction() 
    {

    }

    @Override
    public boolean isFinished()
    {
        return bruh.isTimedOut();
    }
}

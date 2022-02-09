package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class FindBar implements Actionable {
    
    public double degrees;

    public FindBar (double ddegrees) {
        degrees = ddegrees;
    }
    
    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Gettin' that Bar");
        Robot.superClimber.runBothRotato(0.3);
    }



    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
    Robot.superClimber.runBothRotato(0);
    }

    @Override
    public boolean isFinished()
    {
        if (Math.abs(Robot.imu.getCurrentAngle()) >= 2) 
            return true;
        else return false;
    }
}

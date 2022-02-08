package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Sensors.IMU;

public class FindBar implements Actionable {
    
    public double degrees;

    public FindBar (double ddegrees) {
        degrees = ddegrees;
    }
    
    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Gettin' that Bar");
        if (Robot.imu.getCurrentAngle() < 2)   
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
        if (Robot.imu.getCurrentAngle() == 2)  {
            return true;
    }
    
    //TODO fix max not wanting to do actual work
    else {
    return false;
    }
}

}

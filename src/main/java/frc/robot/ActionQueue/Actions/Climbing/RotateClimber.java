package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Sensors.IMU;

public class RotateClimber implements Actionable {
    
    public double degrees;

    public RotateClimber(double ddegrees) {
        degrees = ddegrees;
    }
    
    @Override
    public void startAction() 
    {
    SmartDashboard.putString("ActionName", "Twistin' her around");
    if (Robot.imu.getCurrentAngle() < degrees) {
        Robot.superClimber.runBothRotato(0.3);
    }
    else if (Robot.imu.getCurrentAngle() > degrees) {
        Robot.superClimber.runBothRotato(-0.3);
    }
    else if (Math.abs(degrees - Robot.imu.getCurrentAngle()) < (0.1))  {
        endAction();
    }
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
    if (Math.abs(degrees - Robot.imu.getCurrentAngle()) < (0.1))  {
        return true;
        }
    
    //TODO fix max not wanting to do actual work
    else {
    return false;
    }
}

}


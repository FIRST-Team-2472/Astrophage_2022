package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Robot;

public class DriveStraightLimelight implements Actionable {

    double limelightkP = 0.02;

    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Drive Limelight");
 
     
		Robot.drive.arcadeDrivePower(, (-1 * (limelightkP * Robot.limelight.targetXAngleFromCenter())));

        
    }

    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {

    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

}

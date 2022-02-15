package frc.robot.ActionQueue.Actions.Misc;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Robot;

public class DriveToBall implements Actionable {

    double limelightCorrection = 0.02;
    double distance = Robot.limelight.get_distance_in();

    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Drive Limelight");
        //-1 * (limelightkP * Robot.limelight.targetXAngleFromCenter()));

        
    }

    @Override
    public void periodic() 
    {
        Robot.drive.arcadeDrivePower(0.2, (limelightCorrection * Robot.limelight.targetXAngleFromCenter()));
    }

    @Override
    public void endAction() 
    {
        Robot.drive.arcadeDrivePower(0, 0);
    }

    @Override
    public boolean isFinished()
    {
        if (Robot.limelight.get_distance_in() <= 6) return true;
        else return false;
    }

}

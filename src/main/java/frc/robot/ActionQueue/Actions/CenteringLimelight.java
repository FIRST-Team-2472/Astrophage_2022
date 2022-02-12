package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;


public class CenteringLimelight implements Actionable {

	@Override
	public void startAction() {
		SmartDashboard.putString("ActionName", "Centering Limelight");
	}
	
	private double limelightCorrection = 0.02;

	@Override
	public void periodic() {
		if (Robot.limelight.targetXAngleFromCenter() >= -0.5) {
			Robot.drive.runRight(limelightCorrection * Robot.limelight.targetXAngleFromCenter());
		}
		else if (Robot.limelight.targetXAngleFromCenter() <= 0.5) {
			Robot.drive.runLeft(limelightCorrection * Robot.limelight.targetXAngleFromCenter());
		}
		else if (Math.abs(Robot.limelight.targetXAngleFromCenter()) <= 1);
		endAction();
	}

	@Override
	public void endAction() {
		Robot.drive.arcadeDrive(0, 0);
	}

	@Override
	public boolean isFinished() {
		if (Math.abs(Robot.limelight.targetXAngleFromCenter()) <= 1)
		return true;
		else return false;
    }
}

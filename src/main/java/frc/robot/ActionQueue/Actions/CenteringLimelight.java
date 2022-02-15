package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;
import frc.robot.Sensors.limelight;


public class CenteringLimelight implements Actionable {

	@Override
	public void startAction() {
		SmartDashboard.putString("ActionName", "Centering Limelight");
	}
	
	private double tankDrive = .2;

	@Override
	public void periodic() {
		if (Robot.limelight.targetXAngleFromCenter() < 0) {
			Robot.drive.runRight(tankDrive);
		}
		else if (Robot.limelight.targetXAngleFromCenter() > 0) {
			Robot.drive.runLeft(tankDrive);
		}
		else if (Robot.limelight.targetXAngleFromCenter() == 0);
		endAction();
	}

	@Override
	public void endAction() {
		Robot.drive.arcadeDrive(0, 0);
	}

	@Override
	public boolean isFinished() {
		if (Robot.limelight.isTargetSpotted()) return Math.abs(Robot.limelight.targetXAngleFromCenter()) < 2;
		else return true;
    }
}

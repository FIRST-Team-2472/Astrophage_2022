package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class ZeroEncoders implements Actionable {

    @Override
    public void startAction() {
        Robot.drive.zeroEncoders();
        Robot.superClimber.zeroExtenderEncoders();
    }

    @Override
    public void periodic() {

    }

    @Override
    public void endAction() {

    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}

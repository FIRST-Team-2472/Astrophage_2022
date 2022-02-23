package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class ZeroRotations implements Actionable {

    @Override
    public void startAction() {
    }

    @Override
    public void periodic() {
        if (!Robot.superClimber.isLeftVertical()) Robot.superClimber.runRotationL(-.3);
        else Robot.superClimber.runRotationL(0);

        if (!Robot.superClimber.isRightVertical()) Robot.superClimber.runRotationR(-.3);
        else Robot.superClimber.runRotationR(0);
    }

    @Override
    public void endAction() {
        Robot.superClimber.zeroRotationEncoders();
        Robot.superClimber.runRotationR(0);
    }
    
    @Override
    public boolean isFinished() {
        return Robot.superClimber.isLeftVertical() && Robot.superClimber.isRightVertical();
    }
}
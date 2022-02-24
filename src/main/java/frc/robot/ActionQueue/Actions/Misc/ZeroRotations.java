package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class ZeroRotations implements Actionable {

    @Override
    public void startAction() {
    }

    @Override
    public void periodic() {
        if (!Robot.superClimber.isLeftVertical()) Robot.superClimber.runRotationPowerL(-.4);
        else Robot.superClimber.runRotationPowerL(0);

        if (!Robot.superClimber.isRightVertical()) Robot.superClimber.runRotationPowerR(-.4);
        else Robot.superClimber.runRotationPowerR(0);
    }

    @Override
    public void endAction() {
        Robot.superClimber.zeroRotationEncoders();
        Robot.superClimber.runBothRotationsPower(0);
    }
    
    @Override
    public boolean isFinished() {
        return Robot.superClimber.isLeftVertical() && Robot.superClimber.isRightVertical();
    }
}
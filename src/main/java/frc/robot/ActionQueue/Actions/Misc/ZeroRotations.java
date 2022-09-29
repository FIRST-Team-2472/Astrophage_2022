package frc.robot.ActionQueue.Actions.Misc;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class ZeroRotations implements Actionable {
    double correction;

    @Override
    public void startAction() {
    }

    @Override
    public void periodic() {
        correction = -(Robot.superClimber.getRotationLAngle() - Robot.superClimber.getRotationRAngle()) *0.0000007;

        if (!Robot.superClimber.isLeftVertical()) Robot.superClimber.runRotationPowerL(-.7);
        else Robot.superClimber.runRotationPowerL(0);

        if (!Robot.superClimber.isRightVertical()) Robot.superClimber.runRotationPowerR(-.7+correction);
        else Robot.superClimber.runRotationPowerR(0);
    }

    @Override
    public void endAction() {
        Robot.superClimber.runBothRotationsPower(0,0);
        Robot.superClimber.zeroRotationEncoders();
        
    }
    
    @Override
    public boolean isFinished() {
        return Robot.superClimber.isLeftVertical() && Robot.superClimber.isRightVertical();
    }
}
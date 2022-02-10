package frc.robot.ActionQueue.Actions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class ZeroRotations implements Actionable {

    @Override
    public void startAction() {
        SmartDashboard.putString("ActionName", "Zero Rotations");
    }

    @Override
    public void periodic() {
        if (!Robot.superClimber.getRoationLReverseLimit()) Robot.superClimber.runRotationL(-.3);
        else Robot.superClimber.runRotationL(0);

        if (!Robot.superClimber.getRoationRReverseLimit()) Robot.superClimber.runRotationR(-.3);
        else Robot.superClimber.runRotationR(0);
    }

    @Override
    public void endAction() {
        Robot.superClimber.zeroRotationEncoders();
        Robot.superClimber.runRotationR(0);
    }
    
    @Override
    public boolean isFinished() {
        return Robot.superClimber.getRoationLReverseLimit() && Robot.superClimber.getRoationRReverseLimit();
    }
}
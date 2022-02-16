package frc.robot.RobotMethods;

import frc.robot.Robot;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Runners.ActionQueue;



public class TeleopMethods 
{
    private ActionQueue teleopActions = new ActionQueue();

    public void init(boolean enabled) {
        if (!enabled)  teleopActions.addAction(new ZeroEncoders());
    }

    //All three of these are for drivers communicating with the subsystems.
    public void drive() {
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY(), Robot.rightJoystick.getX());
    }

    public void climb() {
        if ()
    }

    public void shoot() {

    }

    public void manualAutoOveride() {

    }

    public void grabBall() {

    }

    public void manualClimb() {

    }
}

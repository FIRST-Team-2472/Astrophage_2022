package frc.robot.RobotMethods;

import javax.swing.Action;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Runners.ActionQueue;



public class TeleopMethods 
{
    boolean manualOverride = false;
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

    public void seeBall() {

    }

    public void gimmeBall() {
        Robot.actionList.LimelightGrab(teleopActions);
    }

    

    public void manualClimb() {
        if (Robot.leftJoystick.getRawButtonPressed(3)) manualOverride = true;

        if(manualOverride)  {
            Robot.superClimber.runBothExtenders(Robot.xboxcontroller.getLeftY());
            Robot.superClimber.runBothRotations(Robot.xboxcontroller.getRightX());
        }
    }
}

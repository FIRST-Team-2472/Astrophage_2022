package frc.robot.RobotMethods;

import frc.robot.Robot;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Runners.ActionQueue;
import frc.robot.Miscellaneous.Timer;



public class TeleopMethods 
{
    private boolean manualOverride, breakSwitch, TwoB, climbTime;
    private Timer abortTimer = new Timer(.5);

    private ActionQueue teleopActions = new ActionQueue();


    public void init(boolean enabled) {
        if (!enabled)  {
            teleopActions.addAction(new ZeroEncoders());
            Robot.matchTimer.beginMatch();
        }

        manualOverride = false;
        breakSwitch = false;
        TwoB = false;
        climbTime = false;
    }

    //All three of these are for drivers communicating with the subsystems.
    public void drive() {
        Robot.intake.runConveyorPower(0.5);
    }

    public void climb() {
        if (Robot.matchTimer.matchTime() >= 120) climbTime = true;

        if ((manualOverride || climbTime) && Robot.xboxcontroller.getLeftBumperPressed() && Robot.xboxcontroller.getRightBumperPressed())
            Robot.actionList.Climb(teleopActions);
    }

    public void shoot() {
        if (Robot.xboxcontroller.getXButton()) {
            Robot.shooter.runFlyWheelPower(1);
            Robot.intake.runConveyorPower(.5);
        } else {
            Robot.shooter.runFlyWheelPower(0);
            Robot.intake.runConveyorPower(0);
        }
    }

    public void autoStop() {
        if (Robot.xboxcontroller.getYButtonPressed()) {
            if (!breakSwitch) {
                teleopActions.pause();
                breakSwitch = true;
            }
            else {
                Robot.superClimber.runBothExtenders(0);
                Robot.superClimber.runBothRotations(0);
                teleopActions.resume();
                breakSwitch = false;
            }
        }

        if (Robot.xboxcontroller.getBButtonPressed() && TwoB) 
            teleopActions.clear();
        
        if (Robot.xboxcontroller.getBButtonPressed() && !TwoB) {
            TwoB = true;
            abortTimer.reset();
        }

        if (abortTimer.isTimedOut())
            TwoB = false;
    }
/*
    public void seeBall() {
        if (Robot.leftJoystick.getRawButton(1)) 
            Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*.5, (-1 * (0.02 * Robot.limelight.targetXAngleFromCenter())));
    }*/


    public void gimmeBall() {
        if (Robot.rightJoystick.getRawButtonPressed(1))
            Robot.actionList.LimelightGrab(teleopActions);
    }

    public void manualClimb() {
        if (Robot.xboxcontroller.getStartButtonPressed()) manualOverride = true;
/*
        if ((manualOverride || climbTime) && breakSwitch)  {
            Robot.superClimber.runBothExtenders(Robot.xboxcontroller.getLeftY());
            Robot.superClimber.runBothRotations(Robot.xboxcontroller.getRightX());
        }*/
        Robot.superClimber.runBothExtendersPower(Robot.xboxcontroller.getLeftY());
        Robot.superClimber.runBothRotationsPower(Robot.xboxcontroller.getRightX());
    }
}

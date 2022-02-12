package frc.robot.RobotMethods;

import frc.robot.ActionQueue.Actions.CenteringLimelight;
import frc.robot.ActionQueue.Actions.DriveAndConveyor;
import frc.robot.ActionQueue.Actions.DriveStraightFeet;
import frc.robot.ActionQueue.Actions.DriveStraightTime;
import frc.robot.ActionQueue.Actions.ShootBall;
import frc.robot.ActionQueue.Actions.ZeroRotations;
import frc.robot.ActionQueue.Actions.Climbing.*;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class ActionLists 
{
    public void InitialAutonomous(ActionQueue action)
    {
        action.addAction(new ZeroRotations());
        action.addAction(new CenteringLimelight());
        action.addAction(new DriveStraightFeet(-10));
        action.addAction(new DriveStraightFeet(10));
        action.addAction(new ShootBall(.5, .5, 4));
    }

    public void DriveSome(ActionQueue action) 
    {
        action.addAction(new DriveStraightTime(.3, 4));
        action.addAction(new DriveStraightTime(-.3, 4));
        action.addAction(new DriveStraightTime(.3, 4));
    }

    public void Climb(ActionQueue action) {
        action.addAction(new MoveClimber(9999));
        action.addAction(new ClimberIn());
        action.addAction(new ClampOn());
        for (int i = 0; i < 2; i++) {
            //small
            action.addAction(new MoveClimber(99999));
            action.addAction(new ExtendAndRotateClimber(99999, 999999));
            action.addAction(new FindBar());
            action.addAction(new PullToTilt());
            action.addAction(new ClampOff());
            action.addAction(new AlignClimber());
            action.addAction(new ClimberIn());
            action.addAction(new ClampOn());
        }
    }
}

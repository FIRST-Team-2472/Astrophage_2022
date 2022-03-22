package frc.robot.RobotMethods;

import frc.robot.ActionQueue.Actions.Climbing.*;
import frc.robot.ActionQueue.Actions.Misc.*;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class ActionLists 
{
    //What the robot does in the first 15 seconds of autonomous
    public void InitialAutonomous(ActionQueue action)
    {
        action.addAction(new ShootBall(2));
        action.addAction(new DriveStraightTime(-0.5, 4));
    }

    //If the drivers decide that driving is too hard.
    public void DriveSome(ActionQueue action) 
    {
        action.addAction(new DriveStraightTime(.3, 4));
        action.addAction(new DriveStraightTime(-.3, 4));
        action.addAction(new DriveStraightTime(.3, 4));
    }

    public void LimelightGrab(ActionQueue action)
    {
        action.addAction(new CenteringLimelight());
        action.addAction(new DriveToBall());
        action.addAction(new DriveStraightTime(0.2,3));
    }
    

    ////What the robot does when it's time to climb the monkey bars.
    public void Climb(ActionQueue action) {
        action.addAction(new ClimberIn());
        action.addAction(new ClampOn());
        for (int i = 0; i < 2; i++) {
            //small
            //for a small boi
            //on a small street
            //during a small time
            action.addAction(new MoveClimberPower(2));
            action.addAction(new ExtendAndRotateClimber(2, 2));
            action.addAction(new FindBar());
            action.addAction(new PullToTilt());
            action.addAction(new ClampOff());
            action.addAction(new ZeroRotations());
            action.addAction(new ClimberIn());
            action.addAction(new ClampOn());
        }
    }

    public void ClimbTest(ActionQueue action){
        action.addAction(new ClampOff());
        action.addAction(new ClimberIn());
        action.addAction(new ClampOn());
        action.addAction(new ExtendAndRotateClimber(16, 3500000));
        //for finding bar
        action.addAction(new RotateClimber(2950000));
        action.addAction(new PullToTilt());
        action.addAction(new ClampOff());
        action.addAction(new MoveClimberPower(13));
        action.addAction(new ClimbInAndGo90());
        action.addAction(new ClampOn());
        //action.addAction(new RotateClimber(3500000));
        /*action.addAction(new MoveClimberPower(3));
        action.addAction(new MoveClimberPower(0));
        action.addAction(new ClampOn());
        action.addAction(new Wait(2));
        action.addAction(new ClampOff());
        action.addAction(new RotateClimber(1330000));*/

    }
}

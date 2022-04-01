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
        action.addAction(new DriveToBall());
        action.addAction(new EndDriveToBall(1.5));
    }
    

    ////What the robot does when it's time to climb the monkey bars.
    public void Climb(ActionQueue action) {
        action.addAction(new ZeroRotations());
        action.addAction(new ClampOff());
        action.addAction(new ClimberIn());
        action.addAction(new ClampOn());
        action.addAction(new MoveClimberPower(2));
        action.addAction(new ExtendAndRotateClimber(24, 3650000));
        //for finding bar
        action.addAction(new RotateClimber(2950000));
        action.addAction(new PullToTilt());
        action.addAction(new RotateClimber(3100000));
        action.addAction(new PullToTilt(-7));
        action.addAction(new RotateClimber(3620000));
        action.addAction(new PullToTilt(-11));
        action.addAction(new ClampOff());
        action.addAction(new MoveClimberPower(22));
        action.addAction(new ClimbInAndGo90());
        action.addAction(new ClampOn());

        //transveal bar
        action.addAction(new MoveClimberPower(3));
        action.addAction(new ExtendAndRotateClimber(24, 3650000));
        //for finding bar
        action.addAction(new RotateClimber(2950000));
        action.addAction(new STOPswing());
        action.addAction(new PullToTilt());
        //action.addAction(new ClampOff());
    }

    public void ClimbTest(ActionQueue action){
        action.addAction(new MoveClimberPower(10));
        action.addAction(new MoveClimberPower(0));
        action.addAction(new MoveClimberPower(10));
        action.addAction(new MoveClimberPower(0));
        action.addAction(new MoveClimberPower(10));
        action.addAction(new MoveClimberPower(-10));
    }
}

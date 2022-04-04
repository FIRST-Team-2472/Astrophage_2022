package frc.robot.RobotMethods;

import frc.robot.ActionQueue.Actions.Climbing.*;
import frc.robot.ActionQueue.Actions.Misc.*;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class ActionLists 
{
    //What the robot does in the first 15 seconds of autonomous
    public void InitialAutonomous(ActionQueue action)
    {
        //action.addAction(new DriveStraightTime(0.25, 2));
        action.addAction(new ShootBall(5));
        LimelightGrab(action);
        action.addAction(new TurnToDegree(0));
        action.addAction(new DriveStraightTime(-0.15, 2));
        action.addAction(new ShootBall(5));
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
        action.addAction(new EndDriveToBall(1));
    }
    

    ////What the robot does when it's time to climb the monkey bars.
    public void Climb(ActionQueue action) {
        action.addAction(new ZeroRotations());
        //action.addAction(new RotateClimber(10000));
        action.addAction(new ClampOff());
        action.addAction(new ClimberIn());
        action.addAction(new ClampOn());
        action.addAction(new MoveClimberPower(2));
        action.addAction(new RotateClimber(1000000));
        action.addAction(new ExtendAndRotateClimber(24, 3650000));
        //for finding bar
        action.addAction(new RotateClimber(2950000));
        action.addAction(new PullToTilt());
        action.addAction(new RotateClimber(3100000));
        action.addAction(new PullToTilt(-7));
        action.addAction(new RotateClimber(3620000));
        action.addAction(new PullToTilt(-11));
        action.addAction(new RotateClimber(4020000));
        action.addAction(new PullToTilt(-17));
        action.addAction(new ClampOff());
        action.addAction(new RotateClimber(2950000));
        action.addAction(new ClimbInAndGo90());
        action.addAction(new ClampOn());
        action.addAction(new STOPswing());
        //transveal bar
        action.addAction(new MoveClimberPower(3));
        action.addAction(new RotateClimber(2000000));
        action.addAction(new ExtendAndRotateClimber(24, 3650000));
        //for finding bar
        action.addAction(new RotateClimber(2950000));
        
        action.addAction(new PullToTilt());
        action.addAction(new ClampOff());
        action.addAction(new MoveClimberPower(17));
        action.addAction(new ZeroRotations());
    }

    public void ClimbTest(ActionQueue action){
        action.addAction(new MoveClimberPower(10));
        action.addAction(new MoveClimberPower(0));
        action.addAction(new MoveClimberPower(10));
        action.addAction(new MoveClimberPower(0));
        action.addAction(new MoveClimberPower(10));
        action.addAction(new MoveClimberPower(-10));
    }

    public void test(ActionQueue action){
        action.addAction(new TurnToDegree(100));
        action.addAction(new TurnToDegree(0));
        action.addAction(new TurnToDegree(-100));
        action.addAction(new TurnToDegree(0));
    }
}

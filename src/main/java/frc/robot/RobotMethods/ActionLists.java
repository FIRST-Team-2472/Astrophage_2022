package frc.robot.RobotMethods;

import frc.robot.ActionQueue.Actions.DriveAndConveyor;
import frc.robot.ActionQueue.Actions.DriveStraightTime;
import frc.robot.ActionQueue.Actions.ShootBall;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class ActionLists 
{
    public void InitialAutonomous(ActionQueue action)
    {
        action.addAction(new DriveAndConveyor(-.3, .5, .5, 4));
        action.addAction(new DriveStraightTime(.3, 4));
        action.addAction(new ShootBall(.5, .5, 4));
    }

    public void DriveSome(ActionQueue action) 
    {
        action.addAction(new DriveStraightTime(.3, 4));
        action.addAction(new DriveStraightTime(-.3, 4));
        action.addAction(new DriveStraightTime(.3, 4));
    }
}

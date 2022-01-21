package frc.robot.RobotMethods;

import javax.swing.Action;


import frc.robot.ActionQueue.Actions.DriveStraightTime;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class ActionLists {
    
    public void DriveSome(ActionQueue action) {
        action.addAction(new DriveStraightTime(.3, 4));
        action.addAction(new DriveStraightTime(-.3, 4));
        action.addAction(new DriveStraightTime(.3, 4));
    }


}

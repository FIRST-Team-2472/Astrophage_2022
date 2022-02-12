package frc.robot.RobotMethods;

import frc.robot.ActionQueue.Actions.DriveStraightLimelight;
//import frc.robot.ActionQueue.Actions.DriveStraightTime;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class ActionLists {

    public void DriveLimelight(ActionQueue action) {
        action.addAction(new DriveStraightLimelight());
    }
    
    /*public void DriveSome(ActionQueue action) {
        action.addAction(new DriveStraightTime(.3, 4));
        action.addAction(new DriveStraightTime(-.3, 4));
        action.addAction(new DriveStraightTime(.3, 4));
    }*/
}

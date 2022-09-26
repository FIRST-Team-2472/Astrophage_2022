package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.ActionQueue.Runners.DumpBase;

public class ExtendAndRotateClimber extends DumpBase{
    
    public ExtendAndRotateClimber(double feet, double angle) {
        dump.addAction(new MoveClimberPower(feet));
        dump.addAction(new RotateClimber(angle));
    }

}

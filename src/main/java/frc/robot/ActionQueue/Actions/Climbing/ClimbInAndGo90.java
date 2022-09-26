package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.ActionQueue.Actions.Misc.ZeroRotations;
import frc.robot.ActionQueue.Runners.DumpBase;

public class ClimbInAndGo90 extends DumpBase {
    
    public ClimbInAndGo90() {
        dump.addAction(new ClimberIn());
        dump.addAction(new ZeroRotations());
    }
}

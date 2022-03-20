package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.ActionQueue.Actions.Misc.ZeroRotations;
import frc.robot.ActionQueue.Runners.ActionDump;
import frc.robot.ActionQueue.Runners.Actionable;

public class ClimbInAndGo90 implements Actionable {
    ActionDump dump = new ActionDump();
    
    public ClimbInAndGo90() {
        dump.addAction(new ClimberIn());
        dump.addAction(new ZeroRotations());
    }

    public void startAction() {
        dump.runStarts();
    }
	
	public void periodic() {
        dump.running();
    }
	
	public void endAction() {
    }

	public boolean isFinished() {
        return dump.dumpEmpty();
    }
}

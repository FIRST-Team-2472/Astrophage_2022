package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.ActionQueue.Runners.ActionDump;
import frc.robot.ActionQueue.Runners.Actionable;

public class ExtendAndRotateClimber implements Actionable{
    ActionDump dump = new ActionDump();
    
    public ExtendAndRotateClimber(double feet, double angle) {
        dump.addAction(new MoveClimber(feet));
        dump.addAction(new RotateClimber(angle));
    }

    public void startAction() {
        dump.runStarts();
    }
	
	public void periodic() {
        dump.running();
    }
	
	public void endAction() {}

	public boolean isFinished() {
        return dump.dumpEmpty();
    }
}

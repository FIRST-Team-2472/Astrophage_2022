package frc.robot.ActionQueue.Runners;

public class DumpBase implements Actionable{
    public ActionDump dump = new ActionDump();
	
    //In child constructor need to add actions to dump

    //Simplifies the making of actions that use ActionDump
    
	@Override
	public void startAction() {
        dump.runStarts();
	}
	
	@Override
	public void periodic() {
        dump.running();
    }

	@Override
	public void endAction() {};

	@Override
	public boolean isFinished() {
        return dump.isEmpty();
	}
}

package frc.robot.ActionQueue.Runners;

public abstract class TimerBase implements Actionable{
	
	private long endTime;
	private final double lifetime;
	
	public TimerBase (double getLifetime) {
		lifetime = getLifetime;
	}
    
	public void startAction() {
		endTime = System.currentTimeMillis() + (long)(1000 * lifetime);		
	}
	
	public abstract void periodic();

	public abstract void endAction();

	public boolean isFinished() {
		if (System.currentTimeMillis() >= endTime) {
			return true;
		} else {
			return false;
		}
	}
	
	//returns how much time is left
	public String toString() {
		return "Action life:"+lifetime;
		
	}
}

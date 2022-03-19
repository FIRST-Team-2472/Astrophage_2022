package frc.robot.Miscellaneous;

public class Timer {
    private long end;
    private double countdown;

    //Countdown = how long timer is set for.
    public Timer(double seconds) {
        countdown = seconds;
        //"System.currentTimeMillis" is how long program has been running; converts countdown's sec to millisec
        end = System.currentTimeMillis() + (long)(1000 * countdown);
        
    }

    //Returns if timer has exceeded time set.
    public boolean isTimedOut() {
        return end <= System.currentTimeMillis();
    }

    //Resets the timer to the initial value given.
    public void reset() {
        end = System.currentTimeMillis() + (long)(1000 * countdown);
    }

    //Resets the timer to a new value given.
    public void reset(double seconds) {
        end = System.currentTimeMillis() + (long)(1000 * seconds);
    }
}

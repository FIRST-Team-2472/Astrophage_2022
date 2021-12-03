package frc.robot.Miscellaneous;

public class Timer {
    private long end;
    private double countdown;

    public Timer(double seconds) {
        countdown = seconds;
        end = System.currentTimeMillis() + (long)(1000 * countdown);
    }

    public boolean isTimedOut() {
        return end <= System.currentTimeMillis();
    }

    public static boolean tryIsTimedOut(Timer it) {
        if (it == null) {
            return false;
        }
        return it.isTimedOut();
    }

    public void reset() {
        end = System.currentTimeMillis() + (long)(1000 * countdown);
    }

    public void reset(double seconds) {
        end = System.currentTimeMillis() + (long)(1000 * seconds);
    }
}

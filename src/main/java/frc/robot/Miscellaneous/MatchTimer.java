package frc.robot.Miscellaneous;

public class MatchTimer {
    private long matchStart;

    public void beginMatch() {
        matchStart = System.currentTimeMillis();
    }

    public int matchTime() {
        return (int)((System.currentTimeMillis() - matchStart)/1000);
    }
}

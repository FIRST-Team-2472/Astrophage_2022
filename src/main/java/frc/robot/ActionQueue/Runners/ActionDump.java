package frc.robot.ActionQueue.Runners;

import java.util.ArrayList;

public class ActionDump {
    private ArrayList<Actionable> dump;

    public ActionDump() {
        dump = new ArrayList<Actionable>();
    }

    public void addAction(Actionable action) {
        dump.add(action);
    }

    public void runStarts() {
        for (int i = 0; i < dump.size(); i++) 
            (dump.get(i)).startAction();
    }

    public void running() {
        for (int i = 0; i < dump.size(); i++) {
            Actionable singleDump = dump.get(i);
            singleDump.periodic();

            if (singleDump.isFinished()) {
                singleDump.endAction();
                dump.remove(i);
            }
        }
    }

    public boolean dumpEmpty() {
        return dump.size() == 0;
    }
}

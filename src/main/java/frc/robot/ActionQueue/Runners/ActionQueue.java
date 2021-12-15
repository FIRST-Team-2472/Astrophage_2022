package frc.robot.ActionQueue.Runners;

import java.util.ArrayList;

public class ActionQueue {
    private ArrayList<Actionable> queue;
    boolean inProgress, start;

    public ActionQueue() {
        queue = new ArrayList<Actionable>();
        inProgress = false;
        start = true;
    }

    public void addAction(Actionable action) {
        queue.add(action);
    }

    public void step() {
        if (!queue.isEmpty()) {
            Actionable action = queue.get(0);
            if (start == true) {
                action.startAction();
                start = false;
            }
            action.periodic();

            inProgress = true;

            if (action.isFinished()) {
                action.endAction();
                queue.remove(0);

                if (!queue.isEmpty()) {
                    action = queue.get(0);
                    action.startAction();
                }
            }
        } else inProgress = false;
    }

    public void clear() {
        for (int i = 0; i < queue.size(); i++) {
            queue.remove(i);
        }
    }

    public boolean isInProgress() {
        return inProgress;
    }
}

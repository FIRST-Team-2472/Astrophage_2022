//Built to run autonomous actions. step() runs each action in the action queues and then deletes them from the queue when the action is done.

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
            if (start == true) {
                Actionable action = queue.get(0);
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

//Built to run autonomous actions. step() runs each action in the action queues and then deletes them from the queue when the action is done.

package frc.robot.ActionQueue.Runners;

import java.util.ArrayList;

public class ActionQueue {
    private ArrayList<Actionable> queue;
    boolean inProgress, start;
    Actionable runningAction;

    public ActionQueue() {
        queue = new ArrayList<Actionable>();
        inProgress = false;
        start = true;
    }

    public void addAction(Actionable action) {
        queue.add(action);
    }

    //TODO sawyer fix action var is not declared
    public void step() {
        if (!queue.isEmpty()) {
            if (start == true) {
                runningAction = queue.get(0);
                runningAction.startAction();
                start = false;
            }

            runningAction.periodic();

            inProgress = true;

            if (runningAction.isFinished()) {
                runningAction.endAction();
                queue.remove(0);

                if (!queue.isEmpty()) {
                    runningAction = queue.get(0);
                    runningAction.startAction();
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

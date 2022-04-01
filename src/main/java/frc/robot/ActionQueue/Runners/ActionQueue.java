//Built to run autonomous actions. step() runs each action in the action queues and then deletes them from the queue when the action is done.

package frc.robot.ActionQueue.Runners;

import java.util.ArrayList;

import frc.robot.Robot;
import frc.robot.Miscellaneous.Static;

public class ActionQueue {
    private ArrayList<Actionable> queue;
    boolean inProgress, start, breakTime;
    Actionable runningAction;

    public ActionQueue() {
        queue = new ArrayList<Actionable>();
        inProgress = false;
        start = true;
        breakTime = false;
    }

    public void addAction(Actionable action) {
        queue.add(action);
    }

    public void step() {
        if (!queue.isEmpty() && !breakTime) {
            if (start) {
                runningAction = queue.get(0);
                runningAction.startAction();

                start = false;
            }

            // add action's name to suffleboard
            Robot.shuffleBoard.setAction(runningAction.getClass().getSimpleName());

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
        } 
        else {
            if (breakTime) Robot.shuffleBoard.setAction("paused");
            else Robot.shuffleBoard.setAction("done");
            inProgress = false;
        }
    }

    public void clear() {
        while (!queue.isEmpty())
            queue.remove(0);
        start = true;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void pause() {
        breakTime = true;
        start = true;
        //ook snook
        Static.stopAll();
    }

    public void resume() {
        breakTime = false;
    }
}

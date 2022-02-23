//Built to run autonomous actions. step() runs each action in the action queues and then deletes them from the queue when the action is done.

package frc.robot.ActionQueue.Runners;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ActionQueue {
    private ArrayList<Actionable> queue;
    boolean inProgress, start, breakTime;
    //breaktime originally called bruh
    Actionable runningAction;
    NetworkTableEntry actionNameD, actionNameP;

    public ActionQueue() {
        queue = new ArrayList<Actionable>();
        inProgress = false;
        start = true;
        breakTime = false;
        //set up shuffleboard
        try() {
            ShuffleboardTab driverBoard = Shuffleboard.getTab("Driver Board");
            ShuffleboardTab programmerBoard = Shuffleboard.getTab("Programmer Board");
            actionNameD = driverBoard.add("Current Action", "null").getEntry();
            actionNameP = programmerBoard.add("Current Action", "null").getEntry();
    }

    public void addAction(Actionable action) {
        queue.add(action);
    }

    public void step() {
        if (!queue.isEmpty() && !breakTime) {
            if (start == true) {
                runningAction = queue.get(0);
                runningAction.startAction();
                //add action's name to suffleboard
                actionNameD.setString(runningAction.getClass().getSimpleName());
                actionNameP.setString(runningAction.getClass().getSimpleName());
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
        } else {
            actionNameD.setString("done");
            actionNameP.setString("done");
            inProgress = false;
        }
    }

    public void clear() {
        for (int i = 0; i < queue.size(); i++) {
            queue.remove(i);
        }
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void pause() {
        breakTime = true;
        runningAction.endAction();
    }

    public void resume() {
        breakTime = false;
        runningAction.startAction();
    }
}

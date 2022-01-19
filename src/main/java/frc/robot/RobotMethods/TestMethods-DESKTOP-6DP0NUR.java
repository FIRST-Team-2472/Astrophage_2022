package frc.robot.RobotMethods;

import frc.robot.Robot;
import frc.robot.Miscellaneous.Timer;

public class TestMethods {
    private Timer timer = new Timer(2);
    private int switchMotor = 0;
    
    public void resetTimer() {
        //sould be called when test is initalized
        timer.reset();
    }

    public void runEachMotor() {
        //waits to 2 seconds then switches to a new motor
        //runs each motor on the Robot to test it
        if (timer.isTimedOut() && switchMotor <= 2) {
            switchMotor++;
            timer.reset();
        }  

        switch (switchMotor) {
            case 0:
              Robot.drive.runLeft(.75);
              break;
            case 1:
              Robot.drive.runLeft(0);
              Robot.drive.runRight(.75);
              break;
            case 2:
              Robot.drive.runRight(0);

            default:
        }
    }
}

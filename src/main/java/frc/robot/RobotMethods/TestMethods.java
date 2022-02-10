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
        if (timer.isTimedOut() && switchMotor < 8) {
            switchMotor++;
            timer.reset();
        }  

        switch (switchMotor) {
            case 0:
              Robot.drive.runLeft(.5);
              break;
            case 1:
              Robot.drive.runLeft(0);
              Robot.drive.runRight(.5);
              break;
            case 2:
              Robot.drive.runRight(0);
              Robot.intake.runConveyorPower(.5);
              break;
            case 3:
              Robot.intake.runConveyorPower(0);
              Robot.shooter.runFlyWheelPower(.5);
              break;
            case 4:
              Robot.shooter.runFlyWheelPower(0);
              Robot.superClimber.runRotationL(.5);
              break;
            case 5:
              Robot.superClimber.runRotationL(0);
              Robot.superClimber.runExtenderL(.5);
            break;
            case 6:
              Robot.superClimber.runExtenderL(0);
              Robot.superClimber.runRotationR(.5);
              break;
            case 7:
              Robot.superClimber.runRotationR(0);
              Robot.superClimber.runExtenderR(.5);
              break;
            case 8:
              Robot.superClimber.runExtenderR(0);


            default:
        }
    }
}

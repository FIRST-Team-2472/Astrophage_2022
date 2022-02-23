package frc.robot.RobotMethods;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;
import frc.robot.Miscellaneous.Timer;

public class TestMethods {
  private Timer timer;
  private int switchMotor;
  private double testSpeed;
  private ShuffleboardTab testBoard;
  private NetworkTableEntry leftRotationLSSB, rightRotationLSSB, leftSadleLSSB, rightSadleLSSB, leftClawLSSB, rightClawLSSB,
    leftDriveEncoderSB, rightDriveEncoderSB, leftRotationEncoderSB, rightRotationEncoderSB, leftExtenderEncoderSB, rightExtenderEncoderSB;

  private boolean flipLeft = false, flipRight = false, runMotors = false;

  public TestMethods() {
    testBoard = Shuffleboard.getTab("Test Board");

    leftRotationLSSB = testBoard.add("Left Rotation LS", false).getEntry();
    rightRotationLSSB = testBoard.add("Right Rotation LS", false).getEntry();

    leftSadleLSSB = testBoard.add("Left Sadle LS", false).getEntry();
    rightSadleLSSB = testBoard.add("Right Sadle LS", false).getEntry();

    leftClawLSSB = testBoard.add("Left Claw LS", false).getEntry();
    rightClawLSSB = testBoard.add("Right Claw RS", false).getEntry();

    leftDriveEncoderSB = testBoard.add("Left Drive Encoder", 0).getEntry();
    rightDriveEncoderSB = testBoard.add("Right Drive Encoder", 0).getEntry();

    leftRotationEncoderSB = testBoard.add("Left Rotation Encoder", 0).getEntry();
    rightRotationEncoderSB = testBoard.add("Right Rotation Encoder", 0).getEntry();

    leftExtenderEncoderSB = testBoard.add("Left Extender Encoder", 0).getEntry();
    rightExtenderEncoderSB = testBoard.add("Right Extender Encoder", 0).getEntry();

    timer = new Timer(2);
    switchMotor = 0;
    testSpeed = .25;
  }
    
  public void resetTimer() {
    //sould be called when test is initalized
    timer.reset();
  }

  public void runEverything() {
    leftRotationLSSB.setBoolean(Robot.superClimber.isLeftVertical());
    rightRotationLSSB.setBoolean(Robot.superClimber.isRightVertical());
    leftSadleLSSB.setBoolean(Robot.superClimber.isTouchingBarLeft());
    rightSadleLSSB.setBoolean(Robot.superClimber.isTouchingBarRight());
    leftClawLSSB.setBoolean(Robot.climberClamp.isClampedL());
    rightClawLSSB.setBoolean(Robot.climberClamp.isClampedR());

    leftDriveEncoderSB.setDouble(Robot.drive.getLeftFeet());
    rightDriveEncoderSB.setDouble(Robot.drive.getRightFeet());
    leftRotationEncoderSB.setDouble(Robot.superClimber.getRotationLAngle());
    rightRotationEncoderSB.setDouble(Robot.superClimber.getRotationRAngle());
    leftExtenderEncoderSB.setDouble(Robot.superClimber.getExtenderLHeight());
    rightExtenderEncoderSB.setDouble(Robot.superClimber.getExtenderRHeight());

    if (Robot.leftJoystick.getRawButtonPressed(1)) runMotors = !runMotors;
    
    if (runMotors) {
      //waits to 2 seconds then switches to a new motor
      //runs each motor on the Robot to test it
      if (timer.isTimedOut() && switchMotor < 8) {
        switchMotor++;
        timer.reset();
      }  

      switch (switchMotor) {
        case 0:
          Robot.drive.runLeft(testSpeed);
          break;
        case 1:
          Robot.drive.runLeft(0);
          Robot.drive.runRight(testSpeed);
          break;
        case 2:
          Robot.drive.runRight(0);
          Robot.intake.runConveyorPower(testSpeed);
          break;
        case 3:
          Robot.intake.runConveyorPower(0);
          Robot.shooter.runFlyWheelPower(testSpeed);
          break;
        case 4:
          Robot.shooter.runFlyWheelPower(0);
          Robot.superClimber.runRotationL(testSpeed);
          break;
        case 5:
          Robot.superClimber.runRotationL(0);
          Robot.superClimber.runExtenderL(testSpeed);
          break;
        case 6:
          Robot.superClimber.runExtenderL(0);
          Robot.superClimber.runRotationR(testSpeed);
          break;
        case 7:
          Robot.superClimber.runRotationR(0);
          Robot.superClimber.runExtenderR(testSpeed);
          break;
        case 8:
          Robot.superClimber.runExtenderR(0);


        default:
      }
    }
    else {
      Robot.drive.runLeft(0);
      Robot.drive.runRight(0);
      Robot.intake.runConveyorPower(0);
      Robot.shooter.runFlyWheelPower(0);
      Robot.superClimber.runRotationL(0);
      Robot.superClimber.runExtenderL(0);
      Robot.superClimber.runRotationR(0);
      Robot.superClimber.runExtenderR(0);
    }
  }

  public void runPneumatics() {
    if (Robot.leftJoystick.getRawButtonPressed(4)) {
      if (!flipLeft) { 
        Robot.climberClamp.setClampL();
        flipLeft = true;
      }
      else  {
        Robot.climberClamp.disengageClampL();
        flipLeft = false;
      }
    }

    if (Robot.leftJoystick.getRawButtonPressed(5)) {
      if (!flipRight) {
        Robot.climberClamp.setClampR();
        flipRight = true;
      }
      else {
        Robot.climberClamp.disengageClampR();
        flipRight = false;
      }
    }
  }

  double baseSpeed;
  public void testClimber() {
    double climberSpeed = 0;

    climberSpeed+=Robot.leftJoystick.getY();
    
    if (Robot.leftJoystick.getRawButtonPressed(1)) baseSpeed += .05;
    if (Robot.leftJoystick.getRawButtonPressed(2)) baseSpeed += .01;
    if (Robot.leftJoystick.getRawButtonPressed(3)) baseSpeed -= .02;

    if (climberSpeed+baseSpeed >= 1) climberSpeed = 1;
    else climberSpeed+=baseSpeed;

    Robot.superClimber.runBothExtenders(climberSpeed);
  }
}

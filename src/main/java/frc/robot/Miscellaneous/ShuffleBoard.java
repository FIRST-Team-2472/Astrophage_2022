package frc.robot.Miscellaneous;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;


public class ShuffleBoard {

    private Timer record = new Timer(1);
    private NetworkTableEntry clLimitL, clLimitR, roLimitL, roLimitR, exEcoderL, exEcoderR, roEcoderL,
     roEcoderR, exSpeedL, exSpeedR, IMU_X, IMU_Y, IMU_Z, pressure, actionNameP, actionNameD,
     shSpeed, cameraSelection, limelightDistance, matchTime;
    private ComplexWidget cameraDisplay1, cameraDisplay2;

    String temp;

    public ShuffleBoard() {


      ShuffleboardTab driverBoard = Shuffleboard.getTab("Driver Board");
      ShuffleboardTab programmerBoard = Shuffleboard.getTab("Programmer Board");

        
      driverBoard.addCamera("camera 1", "camera1", "mjpeg:http://roboRIO-2016-FRC.local:1181/?action=stream");
      driverBoard.addCamera("camera 2", "camera2", "mjpeg:http://roboRIO-2016-FRC.local:1182/?action=stream");
      clLimitL = programmerBoard.add("Touching Bar Left", false).getEntry();
      clLimitR = programmerBoard.add("Touching Bar Right", false).getEntry();
      roLimitL = programmerBoard.add("Left Arm Vertical", false).getEntry();
      roLimitR = programmerBoard.add("Right Arm Vertical", false).getEntry(); 
      exEcoderL = programmerBoard.add("Arm Distance Left", 0).getEntry();
      exEcoderR = programmerBoard.add("Arm Distance Right", 0).getEntry();
      roEcoderL = programmerBoard.add("Arm Rotation Left", 0).getEntry();
      roEcoderR = programmerBoard.add("Arm Rotation Right", 0).getEntry(); 
      exSpeedL = programmerBoard.add("Arm Speed Left", 0).getEntry();
      exSpeedR = programmerBoard.add("Arm Speed Right", 0).getEntry(); 
      shSpeed = programmerBoard.add("Shooter Speed", 0).getEntry();
      limelightDistance = programmerBoard.add("Limelight Distance", 0).getEntry();


      IMU_X = programmerBoard.add("IMU X", 0).getEntry(); 
      IMU_Y = programmerBoard.add("IMU Y", 0).getEntry();
      IMU_Z = programmerBoard.add("IMU Z", 0).getEntry(); 
      pressure = programmerBoard.add("Pressure", 0).getEntry(); 

      actionNameD = driverBoard.add("Current Action", "nein").getEntry();
      actionNameP = programmerBoard.add("Current Action", "nein").getEntry();

      matchTime = driverBoard.add("Match Time", -1).getEntry();
    }

    public void update() {
      //limitSwtiches
      clLimitL.setBoolean(Robot.climberClamp.isClampedL());
      clLimitR.setBoolean(Robot.climberClamp.isClampedR());
      roLimitL.setBoolean(Robot.superClimber.isLeftVertical());
      roLimitR.setBoolean(Robot.superClimber.isRightVertical());

      //encoders
      exEcoderL.setNumber(Robot.superClimber.getExtenderLHeight());
      exEcoderR.setNumber(Robot.superClimber.getExtenderRHeight());
      roEcoderL.setNumber(Robot.superClimber.getRotationLAngle());
      roEcoderR.setNumber(Robot.superClimber.getRotationRAngle());
      exSpeedL.setNumber(Robot.superClimber.getExtenderLSpeed());
      exSpeedR.setNumber(Robot.superClimber.getExtenderRSpeed());
      shSpeed.setNumber(Robot.shooter.getSpeed());

      //Limelight
      limelightDistance.setNumber(Robot.limelight.get_distance_in());

      //IMU
      IMU_X.setNumber(Robot.imu.getCurrentXAngle());
      IMU_Y.setNumber(Robot.imu.getCurrentYAngle());
      IMU_Z.setNumber(Robot.imu.getCurrentZAngle());

      //Compresser
      pressure.setNumber(Robot.pressureReader.getAverageValue());

      matchTime.setNumber(Robot.matchTimer.matchTime());

      if (record.isTimedOut()) {
        System.out.println(temp + ": " + Robot.superClimber.getExtenderRSpeed() + " " + Robot.superClimber.getExtenderLSpeed());
        record.reset();
      }
    }

    public void setAction(String actionName) {
      temp = actionName;
      actionNameD.setString(actionName);
      actionNameP.setString(actionName);
    }
}


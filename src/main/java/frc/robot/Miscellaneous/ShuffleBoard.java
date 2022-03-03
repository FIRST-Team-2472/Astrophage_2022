package frc.robot.Miscellaneous;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;
import frc.robot.Sensors.limelight;


public class ShuffleBoard {


    private NetworkTableEntry clLimitL, clLimitR, roLimitL, roLimitR, exEcoderL, exEcoderR, roEcoderL,
     roEcoderR, drEcoderL, drEcoderR, IMU_X, IMU_Y, IMU_Z, pressure, actionNameP, actionNameD,
     shSpeed, cameraSelection, limelightDistance;
    private ComplexWidget cameraDisplay1, cameraDisplay2;

    public ShuffleBoard() {


      ShuffleboardTab driverBoard = Shuffleboard.getTab("Driver Board");
      ShuffleboardTab programmerBoard = Shuffleboard.getTab("Programmer Board");

        
      //driverBoard.addCamera("camera 1", "camera1", "mjpeg:http://roboRIO-2016-FRC.local:1181/?action=stream");
      //driverBoard.addCamera("camera 2", "camera2", "mjpeg:http://roboRIO-2016-FRC.local:1182/?action=stream");
      clLimitL = programmerBoard.add("Touching Bar Left", false).getEntry();
      clLimitR = programmerBoard.add("Touching Bar Right", false).getEntry();
      roLimitL = programmerBoard.add("Left Arm Vertical", false).getEntry();
      roLimitR = programmerBoard.add("Right Arm Vertical", false).getEntry(); 
      exEcoderL = programmerBoard.add("Arm Distance Left", 0).getEntry();
      exEcoderR = programmerBoard.add("Arm Distance Right", 0).getEntry();
      roEcoderL = programmerBoard.add("Arm Rotation Left", 0).getEntry();
      roEcoderR = programmerBoard.add("Arm Rotation Right", 0).getEntry(); 
      drEcoderL = programmerBoard.add("Drive Distance Left", 0).getEntry();
      drEcoderR = programmerBoard.add("Drive Distance Right", 0).getEntry(); 
      shSpeed = programmerBoard.add("Shooter Speed", 0).getEntry();
      limelightDistance = programmerBoard.add("Limelight Distance", 0).getEntry();


      IMU_X = programmerBoard.add("IMU X", 0).getEntry(); 
      IMU_Y = programmerBoard.add("IMU Y", 0).getEntry();
      IMU_Z = programmerBoard.add("IMU Z", 0).getEntry(); 
      pressure = programmerBoard.add("Pressure", 0).getEntry(); 

      actionNameD = driverBoard.add("Current Action", "nein").getEntry();
      actionNameP = programmerBoard.add("Current Action", "nein").getEntry();
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
      drEcoderL.setNumber(Robot.drive.getLeftFeet());
      drEcoderR.setNumber(Robot.drive.getRightFeet());
      shSpeed.setNumber(Robot.shooter.getSpeed());

      //Limelight
      limelightDistance.setNumber(Robot.limelight.get_distance_in());

      //IMU
      IMU_X.setNumber(Robot.imu.getCurrentXAngle());
      IMU_Y.setNumber(Robot.imu.getCurrentYAngle());
      IMU_Z.setNumber(Robot.imu.getCurrentZAngle());

      //Compresser
      pressure.setNumber(Robot.pressureReader.getAverageValue());
    }

    public void setAction(String actionName) {
      actionNameD.setString(actionName);
      actionNameP.setString(actionName);
    }
}


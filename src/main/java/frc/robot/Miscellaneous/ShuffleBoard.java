package frc.robot.Miscellaneous;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;


public class ShuffleBoard {

    private Timer record = new Timer(1);
    private NetworkTableEntry clLimitL, clLimitR, roLimitL, roLimitR, exEcoderL, exEcoderR, roEcoderL,
     roEcoderR, exSpeedL, exSpeedR, IMU_X, IMU_Y, IMU_Z, pressure, actionNameP, actionNameD,
     shSpeed, cameraSelection, limelightDistance, limelightArea, matchTime, getTeam, showInvert;
    private ComplexWidget cameraDisplay1, cameraDisplay2;

    private UsbCamera camera1;
    private HttpCamera limelightFeed;
    private VideoSink server;

    String temp;

    //ook snook does the oogie boogie shoogie snoogie
    public ShuffleBoard() {
      ShuffleboardTab driverBoard = Shuffleboard.getTab("Driver Board");
      ShuffleboardTab programmerBoard = Shuffleboard.getTab("Programmer Board");

      camera1 = CameraServer.startAutomaticCapture(0);
      limelightFeed = CameraServer.addAxisCamera("10.24.72.17");
      

      cameraSelection = NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry("CameraSelection");

      server = CameraServer.getServer();

      driverBoard.addCamera("front the of camera the", "camera1", "mjpeg:http://roboRIO-2472-FRC.local:1181/?action=stream");
      driverBoard.addCamera("back the of camera the", "limelightFeed", "mjpeg:http://10.24.72.17:5800");
      

      clLimitL = programmerBoard.add("Touching Bar Left", false).getEntry();
      clLimitR = programmerBoard.add("Touching Bar Right", false).getEntry();
      roLimitL = programmerBoard.add("Left Arm Vertical", false).getEntry();
      roLimitR = programmerBoard.add("Right Arm Vertical", false).getEntry(); 
      exEcoderL = programmerBoard.add("Arm Distance Left", -1).getEntry();
      exEcoderR = programmerBoard.add("Arm Distance Right", -1).getEntry();
      roEcoderL = programmerBoard.add("Arm Rotation Left", -1).getEntry();
      roEcoderR = programmerBoard.add("Arm Rotation Right", -1).getEntry(); 
      exSpeedL = programmerBoard.add("Arm Speed Left", -1).getEntry();
      exSpeedR = programmerBoard.add("Arm Speed Right", -1).getEntry(); 
      shSpeed = programmerBoard.add("Shooter Speed", -1).getEntry();
      limelightDistance = programmerBoard.add("Limelight Distance", -1).getEntry();
      limelightArea = programmerBoard.add("Ball Area", -1).getEntry();


      IMU_X = programmerBoard.add("IMU X", -1).getEntry(); 
      IMU_Y = programmerBoard.add("IMU Y", -1).getEntry();
      IMU_Z = programmerBoard.add("IMU Z", -1).getEntry(); 
      pressure = programmerBoard.add("Pressure", -1).getEntry(); 

      getTeam = driverBoard.add("Toggle Team", false).getEntry();
      showInvert = driverBoard.add("Inverto", false).getEntry();

      actionNameD = driverBoard.add("Current Action", "nein").getEntry();
      actionNameP = programmerBoard.add("Current Action", "nein").getEntry();

      matchTime = driverBoard.add("Match Time", -1).getEntry();
    }

    public void update() {
      //if(false) Robot.limelight.setPipeLine(0);
      //else Robot.limelight.setPipeLine(1);

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
      limelightArea.setNumber(Robot.limelight.targetArea());

      //IMU
      IMU_X.setNumber(Robot.imu.getCurrentXAngle());
      IMU_Y.setNumber(Robot.imu.getCurrentYAngle());
      IMU_Z.setNumber(Robot.imu.getCurrentZAngle());

      //Compresser
      pressure.setNumber(Robot.pressureReader.getAverageValue());

      if (Robot.matchTimer.matchTime() < 300)
        matchTime.setNumber(195-Robot.matchTimer.matchTime());
      else
        matchTime.setNumber(0);
    }

    public void setAction(String actionName) {
      temp = actionName;
      actionNameD.setString(actionName);
      actionNameP.setString(actionName);
    }
    
    public void setCamera(int toggle) {
      if(toggle == 0) server.setSource(camera1);
      else if (toggle == 1) server.setSource(limelightFeed);
    }

    public void setInvert(int oooooo) {
      if(oooooo == 1) showInvert.setBoolean(false);
      else if (oooooo == -1) showInvert.setBoolean(true);
    }
}


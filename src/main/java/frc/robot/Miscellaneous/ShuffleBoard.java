package frc.robot.Miscellaneous;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;


public class ShuffleBoard {

    private UsbCamera camera1;
    private UsbCamera camera2;
    private NetworkTableEntry robotState;
    private ComplexWidget cameraDisplay1, cameraDisplay2;

    public ShuffleBoard() {

      //camera1 = CameraServer.startAutomaticCapture(0);
      //camera2 = CameraServer.startAutomaticCapture(1);
      ShuffleboardTab driverBoard = Shuffleboard.getTab("Driver Board");
      ShuffleboardTab programmerBoard = Shuffleboard.getTab("Programmer Board");
        
      //driverBoard.addCamera("camera 1", "camera1", "mjpeg:http://roboRIO-2016-FRC.local:1181/?action=stream");
      //driverBoard.addCamera("camera 2", "camera2", "mjpeg:http://roboRIO-2016-FRC.local:1182/?action=stream");
        
        
        
    }

    public void update() {

    }

    public void setRobotState(String state) {

    }
}


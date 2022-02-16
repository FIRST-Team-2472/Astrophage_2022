package frc.robot.Miscellaneous;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;
import frc.robot.Sensors.limelight;

public class ShuffleBoard {

    private UsbCamera camera1;
    private UsbCamera camera2;
    private int cameraStream1 = 0;
    private int cameraStream2 = 1;
    private NetworkTableEntry robotState;
    private ShuffleboardTab driverBoard;
    private ComplexWidget cameraDisplay1, cameraDisplay2;

    public ShuffleBoard() {

        camera1 = CameraServer.startAutomaticCapture(0);
        camera2 = CameraServer.startAutomaticCapture(1);

        
        robotState = driverBoard.add("Robot State", "").getEntry();
        driverBoard.addCamera("camera 1", "camera1", "mjpeg:http://roboRIO-2016-FRC.local:1181/?action=stream");
        driverBoard.addCamera("camera 2", "camera2", "mjpeg:http://roboRIO-2016-FRC.local:1182/?action=stream");
        
        
        
    }

    public void update() {

    }

    public void setRobotState(String state) {

    }
}


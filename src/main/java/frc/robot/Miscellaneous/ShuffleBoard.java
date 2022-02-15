package frc.robot.Miscellaneous;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.HttpCamera;
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
    private HttpCamera limelightFeed;
    private ComplexWidget cameraDisplay1, cameraDisplay2;

    public ShuffleBoard() {

        
        //driverBoard.addCamera("camera 1", "camera1", "mjpeg:http://roboRIO-2016-FRC.local:1181/?action=stream");
        //driverBoard.addCamera("camera 2", "camera2", "mjpeg:http://roboRIO-2016-FRC.local:1182/?action=stream");
        //driverBoard.addCamera("Limelight", "Limelight", ""http://limelight.local:5801/stream.mjpg");
        
        
        
    }

    public void update() {

    }

    public void setRobotState(String state) {

    }
}


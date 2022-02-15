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
    private ComplexWidget cameraDisplay1, cameraDisplay2;

    public ShuffleBoard() {

        camera1 = CameraServer.startAutomaticCapture(0);
        camera2 = CameraServer.startAutomaticCapture(1);

        
        
        cameraDisplay1 = Robot.driverBoard.addCamera("camera1", camera1, "USB Camera 0").getEntry();
        

        
        
    }

    public void update() {

    }

    public void setRobotState(String state) {

    }
}


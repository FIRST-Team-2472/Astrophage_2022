package frc.robot.Miscellaneous;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;
import frc.robot.Subsystems.SuperClimber;

public class ShuffleBoard {

    private UsbCamera camera1;
    private UsbCamera camera2;
    private HttpCamera limelightFeed;
    private ComplexWidget cameraDisplay1, cameraDisplay2;
    private NetworkTableEntry leftVel, leftPos, rightVel, rightPos;

    public ShuffleBoard() {

        ShuffleboardTab ree = Shuffleboard.getTab("Programmer Board");
        //driverBoard.addCamera("camera 1", "camera1", "mjpeg:http://roboRIO-2016-FRC.local:1181/?action=stream");
        //driverBoard.addCamera("camera 2", "camera2", "mjpeg:http://roboRIO-2016-FRC.local:1182/?action=stream");
        //driverBoard.addCamera("Limelight", "Limelight", ""http://limelight.local:5801/stream.mjpg");
        rightVel = ree.add("right Vel", 0).getEntry();
        rightPos = ree.add("right Pos", 0).getEntry();
        leftVel = ree.add("left Vel", 0).getEntry();
        leftPos = ree.add("left Pos", 0).getEntry();
        
    }

    public void update() {
        rightPos.setNumber(Robot.drive.getRightFeet());
        rightVel.setNumber(Robot.drive.getRightSpeed());
        leftPos.setNumber(Robot.drive.getLeftFeet());
        leftVel.setNumber(Robot.drive.getLeftSpeed());

        

    }


}


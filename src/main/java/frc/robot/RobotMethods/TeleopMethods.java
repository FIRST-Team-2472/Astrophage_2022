package frc.robot.RobotMethods;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Actions.Misc.ZeroRotations;
import frc.robot.ActionQueue.Runners.ActionQueue;
import frc.robot.Miscellaneous.Timer;



public class TeleopMethods 
{
    private NetworkTableEntry cameraSelection;
    private boolean breakSwitch, TwoB, climbTime, flipInvert;
    private Timer abortTimer = new Timer(.5);
    private UsbCamera camera1;
    private UsbCamera camera2;
    private VideoSink server;
    private ActionQueue teleopActions = new ActionQueue();
    private double driveSpeed = 1;




    public void init(boolean enabled) {
        cameraSelection = NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry("CameraSelection");

        server = CameraServer.getServer();

        if (!enabled)  {
            teleopActions.addAction(new ZeroEncoders());
            teleopActions.addAction(new ZeroRotations());
            Robot.matchTimer.beginMatch();
        }

        flipInvert = false;
        breakSwitch = false;
        TwoB = false;
        climbTime = false;
    }

    public void update()
    {
        teleopActions.step();
    }

    //All three of these are for drivers communicating with the subsystems.
    public void drive() {
        if(Robot.leftJoystick.getRawButton(1)) driveSpeed = 0.5;
        else driveSpeed = 1;
        
        camera1 = CameraServer.startAutomaticCapture(0);
        camera2 = CameraServer.startAutomaticCapture(1);

        if (Robot.leftJoystick.getRawButtonPressed(3) && flipInvert) {
            System.out.println("Setting camera 2");
            server.setSource(camera2);
            flipInvert = false;
            driveSpeed = Math.abs(driveSpeed);
        } else if (Robot.leftJoystick.getRawButtonPressed(3) && !flipInvert) {
            System.out.println("Setting camera 1");
            server.setSource(camera1);
            flipInvert = true;
            driveSpeed = Math.abs(driveSpeed) * -1;
        }
        
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY() *0.5 * driveSpeed, Robot.rightJoystick.getX() *0.5 *driveSpeed);
    }

    public void climb() {
        if (Robot.matchTimer.matchTime() >= 120) climbTime = true;

        if (climbTime && Robot.xboxcontroller.getLeftBumperPressed() && Robot.xboxcontroller.getRightBumperPressed())
            Robot.actionList.Climb(teleopActions);
    }

    public void shoot() {
        if (Robot.xboxcontroller.getXButton()) {
            Robot.shooter.runFlyWheelPower(1);
            if(Robot.shooter.getSpeed() < -60000)Robot.intake.runConveyorPower(.5);
        }
        if(Robot.xboxcontroller.getXButtonReleased()) {
            Robot.shooter.runFlyWheelPower(0);
            Robot.intake.runConveyorPower(0);
        }
    }

    public void convey() {
        if (Robot.xboxcontroller.getAButton()) Robot.intake.runConveyorPower(0.5);
        if (Robot.xboxcontroller.getAButtonReleased()) Robot.intake.runConveyorPower(0);
        if (Robot.xboxcontroller.getRightBumper()) Robot.intake.runConveyorPower(-0.5);
        if (Robot.xboxcontroller.getRightBumperReleased()) Robot.intake.runConveyorPower(0);
    }
    
    public void autoStop() {
        if (Robot.xboxcontroller.getYButtonPressed()) {
            if (!breakSwitch) {
                teleopActions.pause();
                breakSwitch = true;
            }
            else {
                Robot.superClimber.runBothExtenders(0);
                Robot.superClimber.runBothRotations(0);
                teleopActions.resume();
                breakSwitch = false;
            }
        }

        if (Robot.xboxcontroller.getBButtonPressed() && TwoB) 
            teleopActions.clear();
        
        if (Robot.xboxcontroller.getBButtonPressed() && !TwoB) {
            TwoB = true;
            abortTimer.reset();
        }

        if (abortTimer.isTimedOut())
            TwoB = false;
    }

    public void seeBall() {
        if (Robot.leftJoystick.getRawButton(1)) 
            Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*.5, (-0.4 * (0.01 * Robot.limelight.targetXAngleFromCenter())));
    }


    public void gimmeBall() {
        if (Robot.rightJoystick.getRawButtonPressed(1))
            Robot.actionList.LimelightGrab(teleopActions);
    }

    public void manualClimb() {
        
        if (climbTime)  {
            double bruh = -(Robot.superClimber.getExtenderLHeight() - Robot.superClimber.getExtenderRHeight()) * 0.00001;
            double bruh2 = (Robot.superClimber.getRotationLAngle() - Robot.superClimber.getRotationRAngle()) * 0.00001;
            if(Math.abs(Robot.xboxcontroller.getLeftY()) > 0.1)Robot.superClimber.runBothExtendersPower(Robot.xboxcontroller.getLeftY(), Robot.xboxcontroller.getLeftY() + bruh);
            else Robot.superClimber.runBothExtendersPower(0, 0);
            if(Math.abs(Robot.xboxcontroller.getRightY()) > 0.1)Robot.superClimber.runBothRotationsPower(Robot.xboxcontroller.getRightY(), Robot.xboxcontroller.getRightY() + bruh2);
            else Robot.superClimber.runBothExtendersPower(0, 0);
        }
    }
}

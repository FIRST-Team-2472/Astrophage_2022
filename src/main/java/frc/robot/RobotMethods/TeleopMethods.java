package frc.robot.RobotMethods;

import frc.robot.Robot;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Actions.Misc.ZeroRotations;
import frc.robot.ActionQueue.Runners.ActionQueue;
import frc.robot.Miscellaneous.Timer;



public class TeleopMethods 
{
    private NetworkTableEntry cameraSelection;
    private boolean manualOverride, breakSwitch, TwoB, climbTime;
    private Timer abortTimer = new Timer(.5);
    private UsbCamera camera1;
    private UsbCamera camera2;
    private VideoSink server;

    private ActionQueue teleopActions = new ActionQueue();
    private double driveSpeed = 1;

    cameraSelection = NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry("CameraSelection");

    server = CameraServer.getServer();


    public void init(boolean enabled) {
        if (!enabled)  {
            teleopActions.addAction(new ZeroEncoders());
            teleopActions.addAction(new ZeroRotations());
            Robot.matchTimer.beginMatch();
        }

        manualOverride = false;
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

        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY() *0.5 * driveSpeed, Robot.leftJoystick.getX() *0.5 *driveSpeed);

    }

    public void climb() {
        if (Robot.matchTimer.matchTime() >= 120) climbTime = true;

        if ((manualOverride || climbTime) && Robot.xboxcontroller.getLeftBumperPressed() && Robot.xboxcontroller.getRightBumperPressed())
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
            Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*.5, (-1 * (0.02 * Robot.limelight.targetXAngleFromCenter())));
    }


    public void gimmeBall() {
        if (Robot.rightJoystick.getRawButtonPressed(1))
            Robot.actionList.LimelightGrab(teleopActions);
    }

    public void manualClimb() {
        if (Robot.xboxcontroller.getStartButtonPressed()) manualOverride = true;
        
        if (manualOverride || climbTime)  {
            Robot.superClimber.runBothExtenders(Robot.xboxcontroller.getLeftY());
            Robot.superClimber.runBothRotations(Robot.xboxcontroller.getRightX());
        }
    }

    public void cameraInversion() {
    camera1 = CameraServer.startAutomaticCapture(0);
    camera2 = CameraServer.startAutomaticCapture(1);

    if (Robot.leftJoystick.getRawButtonPressed(3)) {
      System.out.println("Setting camera 2");
      server.setSource(camera2);
    } else if (Robot.leftJoystick.getRawButtonReleased(3)) {
      System.out.println("Setting camera 1");
      server.setSource(camera1);
        
    }
}

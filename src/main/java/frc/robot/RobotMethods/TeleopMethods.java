package frc.robot.RobotMethods;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.Robot;
import frc.robot.ActionQueue.Actions.Climbing.MoveClimberPower;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Actions.Misc.ZeroRotations;
import frc.robot.ActionQueue.Runners.ActionQueue;
import frc.robot.Miscellaneous.Timer;



public class TeleopMethods 
{

    private boolean breakSwitch, TwoB, climbTime, flipInvert;
    private Timer abortTimer = new Timer(2);

    private ActionQueue teleopActions;
    private double driveSpeed;
    private int invert;




    public void init(boolean enabled, boolean teamColor) {
        teleopActions = new ActionQueue();        

        if (!enabled)  {
            teleopActions.addAction(new ZeroEncoders());
            teleopActions.addAction(new ZeroRotations());
            Robot.matchTimer.beginMatch();
           /* if (teamColor) 
                Robot.limelight.setPipeLine(1);
            else Robot.limelight.setPipeLine(1);*/
        }

        flipInvert = false;
        breakSwitch = false;
        TwoB = false;
        climbTime = false;
        driveSpeed = 1;
        invert = 1;
    }

    public void update()
    {
        teleopActions.step();
    }

    //All three of these are for drivers communicating with the subsystems.
    public void drive() {
        if (Robot.rightJoystick.getRawButton(1)) driveSpeed = 0.5;
        else if (Robot.rightJoystick.getRawButtonReleased(1)) driveSpeed = 1;
        

        if (Robot.rightJoystick.getRawButtonPressed(3)) {
            if (flipInvert) {
                //System.out.println("Setting camera 2");
                //Robot.shuffleBoard.setCamera(1);
                flipInvert = false;
                invert = Math.abs(invert);
            }
            else {
                //System.out.println("Setting camera 1");
                //Robot.shuffleBoard.setCamera(0);
                flipInvert = true;
                invert = Math.abs(invert) * -1;
            }
        }
        
        Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY() *Math.abs(Robot.leftJoystick.getY()) * driveSpeed *invert, -Robot.rightJoystick.getX() *Math.abs(Robot.rightJoystick.getX()) *driveSpeed*invert);
    }

    public void climb() {
        if (Robot.matchTimer.matchTime() >= 120) climbTime = true;

        if (Robot.xboxcontroller.getLeftBumper() && Robot.xboxcontroller.getRightBumper() && !teleopActions.isInProgress()) {
            Robot.actionList.Climb(teleopActions);
            Robot.matchTimer.beginMatch();
        }

        if(Robot.xboxcontroller.getLeftTriggerAxis() > 0.9)
            teleopActions.addAction(new MoveClimberPower(17));
    }

    public void shoot() {
        if (Robot.xboxcontroller.getXButtonPressed()) Robot.climberClamp.setClamps();

        if (Robot.xboxcontroller.getXButton()) {
            Robot.xboxcontroller.setRumble(RumbleType.kLeftRumble, 1);
            Robot.xboxcontroller.setRumble(RumbleType.kRightRumble, 1);
            Robot.shooter.runFlyWheelVelocity(0.5);
            //Robot.intake.runConveyorPower(.75);
            if(Robot.shooter.getSpeed() < -30000) Robot.intake.runConveyorPower(.75);
            else Robot.intake.runConveyorPower(0);
        }
        
        if(Robot.xboxcontroller.getXButtonReleased()) {
            Robot.climberClamp.disengageClamps();
            Robot.xboxcontroller.setRumble(RumbleType.kLeftRumble, 0);
            Robot.xboxcontroller.setRumble(RumbleType.kRightRumble, 0);
            Robot.shooter.runFlyWheelVelocity(0);
            Robot.intake.runConveyorPower(0);
        }
    }

    public void convey() {
        if (Robot.leftJoystick.getRawButton(3)) Robot.intake.runConveyorPower(0.5);
        if (Robot.leftJoystick.getRawButtonReleased(3)) Robot.intake.runConveyorPower(0);
        if (Robot.leftJoystick.getRawButton(2)) Robot.intake.runConveyorPower(-0.5);
        if (Robot.leftJoystick.getRawButtonReleased(2)) Robot.intake.runConveyorPower(0);
    }
    
    public void autoStop() {
        if (Robot.xboxcontroller.getYButtonPressed()) {
            if (!breakSwitch) {
                teleopActions.pause();
                breakSwitch = true;
            }
            else {
                teleopActions.resume();
                breakSwitch = false;
            }
        }

        if (Robot.xboxcontroller.getBButtonPressed()) {
            if (TwoB) {
                teleopActions.clear();
                TwoB = false;
            }
            else {
                TwoB = true;
                abortTimer.reset();
            }
        }

        if (abortTimer.isTimedOut())
            TwoB = false;
    }

    public void seeBall() {
        if (Robot.leftJoystick.getRawButton(1)) {
            Robot.drive.arcadeDrivePower(Robot.leftJoystick.getY()*-.5, (-0.4 * (0.01 * Robot.limelight.targetXAngleFromCenter())));
            Robot.intake.runConveyorPower(0.5);
        }

        if (Robot.leftJoystick.getRawButtonReleased(1)) Robot.intake.runConveyorPower(0);
    }


    public void gimmeBall() {
        if (Robot.rightJoystick.getRawButton(2)) {
            Robot.drive.arcadeDrivePower(0.4, (-0.4 * (0.01 * Robot.limelight.targetXAngleFromCenter())));
            Robot.intake.runConveyorPower(0.5);  
        }
        if (Robot.rightJoystick.getRawButtonReleased(2)) Robot.intake.runConveyorPower(0);
    }

    public void manualClimb() {
        //if (Robot.xboxcontroller.getYButtonPressed()) Robot.climberClamp.setClamps();

        //if (climbTime)  {
            double correctionEx = (Robot.superClimber.getExtenderLHeight() - Robot.superClimber.getExtenderRHeight()) *1.6;
            double correctionRo = -(Robot.superClimber.getRotationLAngle() - Robot.superClimber.getRotationRAngle()) *0.0000007;
            if(Math.abs(Robot.xboxcontroller.getLeftY()) > 0.1)Robot.superClimber.runBothExtendersPower(-Robot.xboxcontroller.getLeftY()*0.4, -Robot.xboxcontroller.getLeftY()*0.4 + correctionEx);
            else Robot.superClimber.runBothExtendersPower(0, 0);
            if(Math.abs(Robot.xboxcontroller.getRightY()) > 0.1)Robot.superClimber.runBothRotationsPower(-Robot.xboxcontroller.getRightY(), -Robot.xboxcontroller.getRightY() + correctionRo);
            else Robot.superClimber.runBothRotationsPower(0, 0);
        //}
    }

    public boolean isActionGoing(){
        return teleopActions.isInProgress();
    }
}

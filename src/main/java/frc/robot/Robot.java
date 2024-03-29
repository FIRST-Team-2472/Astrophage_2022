
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;


import frc.robot.Miscellaneous.*;
import frc.robot.RobotMethods.*;
import frc.robot.Sensors.*;

import frc.robot.Subsystems.*;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Actions.Misc.ZeroRotations;

import frc.robot.ActionQueue.Runners.ActionQueue;
//yip yiped

public class Robot extends TimedRobot {
  //These declare an instance of a script as a variable and setup the constant talons or other objects.
  public static ClimberClamp climberClamp = new ClimberClamp(Constants.clampLForward, Constants.clampLBackward, Constants.clampRForward, Constants.clampRBackward, Constants.clawLimitL, Constants.clawLimitR);
  public static SuperClimber superClimber = new SuperClimber(Constants.climberExL, Constants.climberExR, Constants.climberRoL, Constants.climberRoR);
  public static Drive drive = new Drive(Constants.motorBR, Constants.motorFR, Constants.motorBL, Constants.motorFL);
  public static Intake intake = new Intake(Constants.conveyor);
  public static Shooter shooter = new Shooter(Constants.flyWheel);
  public static Joystick rightJoystick = new Joystick(Constants.jstickR);
  public static Joystick leftJoystick = new Joystick(Constants.jstickL);
  //public static DistanceSensor distanceSensor = new DistanceSensor();
  public static Compressor compressor = new Compressor(Constants.COMPRESSOR, PneumaticsModuleType.CTREPCM);
  //public static ColorSensor colorSensor = new ColorSensor();
  public static edu.wpi.first.wpilibj.XboxController xboxcontroller = new XboxController(Constants.xboxcontroller);
  public static Limelight limelight = new Limelight();
  public static IMU imu = new IMU(Constants.pigeonID);
  //private DigitalInput switchOne = new DigitalInput(158698);
  //private DigitalOutput Arduino  = new DigitalOutput(4);
  public static MatchTimer matchTimer = new MatchTimer();
  public static ShuffleBoard shuffleBoard = new ShuffleBoard();
  public static AnalogInput pressureReader = new AnalogInput(3);
  public static ActionLists actionList = new ActionLists();
  public TeleopMethods teleopMethods = new TeleopMethods();
  public TestMethods testMethods = new TestMethods();


  public ActionQueue autoActions;
  
  private NetworkTableEntry getTeamColor, robotState;
  private NetworkTableInstance inst;

  boolean enabled = false;

  @Override
  //Robot does this when waking up
  public void robotInit() {
    ShuffleboardTab driverBoard = Shuffleboard.getTab("Driver Board");
    ShuffleboardTab programmerBoard = Shuffleboard.getTab("Programmer Board");
    robotState = driverBoard.add("Robot State", "on").getEntry();

    //declare a default instance of to access FMSInfo
    inst = NetworkTableInstance.getDefault();
    getTeamColor = inst.getTable("FMSInfo").getEntry("IsRedAlliance");

    //runs the compressor
    compressor.enabled();
  }

  @Override
  //Robot constantly does this at all times
  public void robotPeriodic() {
    shuffleBoard.update();
  }


  @Override
  //Robot does this when starting "autonomous" mode
  public void autonomousInit() {
    if (getTeamColor.getBoolean(true)) 
      limelight.setPipeLine(0);
    else limelight.setPipeLine(1);

    imu.zero();
    autoActions = new ActionQueue();

    robotState.setString("Autonomous");

    autoActions.addAction(new ZeroEncoders());
    autoActions.addAction(new ZeroRotations());

    actionList.InitialAutonomous(autoActions);

    
    matchTimer.beginMatch();
    enabled = true;

    //Arduino.disablePWM();
  }

  @Override
  //Robot does this constantly when in "autonomous" mode
  public void autonomousPeriodic() {
    autoActions.step();
  }

  @Override
  //Robot does this when starting "teleop" (human controlled) mode
  public void teleopInit() {
    robotState.setString("Teleop");

    teleopMethods.init(enabled, getTeamColor.getBoolean(true));
  }

  @Override
  //Robot does this constantly when in "teleop" (human controlled) mode
  public void teleopPeriodic() {

    if(!teleopMethods.isActionGoing()){
      teleopMethods.drive();

      teleopMethods.shoot();

      teleopMethods.convey();

     // teleopMethods.gimmeBall();

      teleopMethods.manualClimb();

      teleopMethods.climb();

      teleopMethods.seeBall();
    }

    teleopMethods.autoStop();

    teleopMethods.update();
  }



  @Override
  //Robot does this when starting "test" mode
  public void testInit() {
    robotState.setString("Testing");
    testMethods.resetTimer();
    superClimber.zeroExtenderEncoders();
  }

  @Override
  //Robot does this constantly when in "test" mode
  public void testPeriodic() {
    testMethods.setupClimber();

    //testMethods.runEverything();

    if (leftJoystick.getRawButton(8)) climberClamp.setClamps();
    else if (leftJoystick.getRawButton(7)) climberClamp.disengageClamps();
  }



  @Override
  //Robot does this when starting "disabled" mode
  public void disabledInit() {
    robotState.setString("Off");
    enabled = false;
    matchTimer.reset();
    Static.stopAll();
  }


}

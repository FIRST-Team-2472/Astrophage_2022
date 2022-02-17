
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import frc.robot.Miscellaneous.*;
import frc.robot.RobotMethods.*;
import frc.robot.Sensors.*;

import frc.robot.Subsystems.*;
import frc.robot.ActionQueue.Actions.Misc.ZeroEncoders;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class Robot extends TimedRobot {
  //These declare an instance of a script as a variable and setup the constant talons or other objects.
  public static ClimberClamp climberClamp = new ClimberClamp(Constants.clamp1Forward, Constants.clamp1Backward, Constants.clamp2Forward, Constants.clamp2Backward, Constants.clawLimitL, Constants.clawLimitR);
  public static SuperClimber superClimber = new SuperClimber(Constants.climberEx1, Constants.climberEx2, Constants.climberRo1, Constants.climberRo2,
    Constants.barStopperL, Constants.barStopperR);
  public static Drive drive = new Drive(Constants.motorBR, Constants.motorFR, Constants.motorBL, Constants.motorFL);
  public static Intake intake = new Intake(Constants.conveyor);
  public static Shooter shooter = new Shooter(Constants.flyWheel);
  public static Joystick rightJoystick = new Joystick(Constants.jstickR);
  public static Joystick leftJoystick = new Joystick(Constants.jstickL);
  public static DistanceSensor distanceSensor = new DistanceSensor();
  public static Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  public static ColorSensor colorSensor = new ColorSensor();
  public static edu.wpi.first.wpilibj.XboxController xboxcontroller = new XboxController(Constants.xboxcontroller);
  public static limelight limelight = new limelight();
  public static IMU imu = new IMU(Constants.pigeonID);
  private DigitalInput input;
  private DigitalInput switchOne = new DigitalInput(1);
  private DigitalOutput Arduino  = new DigitalOutput(4);
  public static ShuffleboardTab driverBoard, programmerBoard;


  public static ActionLists actionList = new ActionLists();
  public TeleopMethods teleopMethods = new TeleopMethods();
  public TestMethods testMethods = new TestMethods();

  public ActionQueue autoActions = new ActionQueue();
  private NetworkTableEntry getTeamColor, robotState;
  private NetworkTableInstance inst;

 boolean enabled = false;

  @Override
  //Robot does this when waking up
  public void robotInit() {
    driverBoard = Shuffleboard.getTab("Driver Board");
    programmerBoard = Shuffleboard.getTab("Programmer Board");
    robotState = driverBoard.add("Robot State", "on").getEntry();

    //declare a default instance of to access FMSInfo
    inst = NetworkTableInstance.getDefault();
    getTeamColor = inst.getTable("FMSInfo").getEntry("IsRedAlliance");
    
    if (getTeamColor.getBoolean(true)) 
      limelight.setPipeLine(0);
    else limelight.setPipeLine(3);

    //runs the compressor
    compressor.enabled();
  }

  @Override
  //Robot constantly does this at all times
  public void robotPeriodic() {}
  //Vibe


  @Override
  //Robot does this when starting "autonomous" mode
  public void autonomousInit() {
    robotState.setString("Autonomous");

    autoActions.addAction(new ZeroEncoders());
    enabled = true;

    actionList.DriveSome(autoActions);
    Arduino.disablePWM();
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

    teleopMethods.init(enabled);
  }

  @Override
  //Robot does this constantly when in "teleop" (human controlled) mode
  public void teleopPeriodic() {
    teleopMethods.drive();

    teleopMethods.shoot();

    teleopMethods.gimmeBall();

    teleopMethods.manualClimb();

    teleopMethods.seeBall();

    teleopMethods.autoStop();
  }



  @Override
  //Robot does this when starting "test" mode
  public void testInit() {
    robotState.setString("Testing");
    testMethods.resetTimer();
  }

  @Override
  //Robot does this constantly when in "test" mode
  public void testPeriodic() {
    testMethods.runEverything();

    testMethods.runPneumatics();
  }



  @Override
  //Robot does this when starting "disabled" mode
  public void disabledInit() {
    robotState.setString("Off");
  }
}

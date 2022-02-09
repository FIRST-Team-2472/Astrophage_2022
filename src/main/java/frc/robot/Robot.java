
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import frc.robot.Miscellaneous.*;
import frc.robot.RobotMethods.*;
import frc.robot.Sensors.ColorSensor;
import frc.robot.Sensors.DistanceSensor;
import frc.robot.Sensors.IMU;
import frc.robot.Sensors.limelight;
import frc.robot.Subsystems.*;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class Robot extends TimedRobot {
  public static ClimberClamp climberClamp = new ClimberClamp(Constants.clamp1Forward, Constants.clamp1Backward, Constants.clamp2Forward, Constants.clamp2Backward, Constants.clawLimitL, Constants.clawLimitR);
  public static SuperClimber superClimber = new SuperClimber(Constants.climberEx1, Constants.climberEx2, Constants.climberRo1, Constants.climberRo2,
    Constants.barStopperL, Constants.barStopperR, Constants.rotationLimitL, Constants.rotationLimitR);
  public static Drive drive = new Drive(Constants.motorBR, Constants.motorFR, Constants.motorBL, Constants.motorFL);
  public static Intake intake = new Intake(Constants.conveyor);
  public static Shooter shooter = new Shooter(Constants.flyWheel);
  //These declare an instance of a script as a variable and setup the constant talons or other objects.
  public static Joystick rightJoystick = new Joystick(Constants.jstickR);
  public static Joystick leftJoystick = new Joystick(Constants.jstickL);
  public static DistanceSensor distanceSensor = new DistanceSensor();
  public static ColorSensor colorSensor = new ColorSensor();
  public static edu.wpi.first.wpilibj.XboxController xboxcontroller = new XboxController(Constants.xboxcontroller);
  public static limelight limelight = new limelight();
  public static IMU imu = new IMU();
  private DigitalInput input;
  private DigitalInput switchOne;
  private DigitalOutput Arduino;


  public ActionLists actionList = new ActionLists();
  public TeleopMethods teleopMethods = new TeleopMethods();
  public TestMethods testMethods = new TestMethods();

  private ActionQueue autoActions = new ActionQueue();
  private ActionQueue teleopActions = new ActionQueue();

  @Override
  //Robot does this when waking up
  public void robotInit() {
    SmartDashboard.putString("RobotState", "Robot Disabled");
    switchOne = new DigitalInput(1);
    Arduino = new DigitalOutput(4);
  }

  @Override
  //Robot constantly does this at all times
  public void robotPeriodic() {}
  //Vibe


  @Override
  //Robot does this when starting "autonomous" mode
  public void autonomousInit() {
    SmartDashboard.putString("RobotState", "Autonomous");
    actionList.DriveSome(autoActions);
    Arduino.disablePWM();
    Arduino.set(true);
  }

  @Override
  //Robot does this constantly when in "autonomous" mode
  public void autonomousPeriodic() {
    autoActions.step();
  }



  @Override
  //Robot does this when starting "teleop" (human controlled) mode
  public void teleopInit() {
    teleopMethods.init();
  }

  @Override
  //Robot does this constantly when in "teleop" (human controlled) mode
  public void teleopPeriodic() {
    teleopMethods.drive();

    SmartDashboard.putNumber("Distance", distanceSensor.getDistance());
    //SmartDashboard.putNumber("Seeing Black?", colorSensor.getShade());
  }



  @Override
  //Robot does this when starting "test" mode
  public void testInit() {
    SmartDashboard.putString("RobotState", "Testing");
    testMethods.resetTimer();
  }

  @Override
  //Robot does this constantly when in "test" mode
  public void testPeriodic() {
    testMethods.runEachMotor();
  }



  @Override
  //Robot does this when starting "disabled" mode
  public void disabledInit() {
    SmartDashboard.putString("RobotState", "Robot Disabled");
    
  }
}

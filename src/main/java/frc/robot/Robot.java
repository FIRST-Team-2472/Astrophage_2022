
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import frc.robot.Miscellaneous.*;
import frc.robot.RobotMethods.*;
/*import frc.robot.Sensors.ColorSensor;
import frc.robot.Sensors.DistanceSensor;*/
import frc.robot.Subsystems.*;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class Robot extends TimedRobot {
  public static ClimberClamp climberClamp = new ClimberClamp(Constants.ClampOne, Constants.ClampTwo);
  public static ClimberClaw climberClaw = new ClimberClaw(Constants.climberClawEins, Constants.climberClawZwei);
  public static ClimberMove climberMove = new ClimberMove(Constants.climberMoveEins, Constants.climberMoveZwei);
  public static Drive drive = new Drive(Constants.motorBR, Constants.motorFR, Constants.motorBL, Constants.motorFL);
  public static Intake intake = new Intake(Constants.conveyor, Constants.frontWheels, Constants.pcmID, Constants.frontWheelForwardID, Constants.frontWheelBackID);
  public static Shooter shooter = new Shooter(Constants.flyWheel);
// These declare an instance of a script as a variable and setup the constant talons or other objects.
  public static Joystick rightJoystick = new Joystick(Constants.jstickR);
  public static Joystick leftJoystick = new Joystick(Constants.jstickL);
  public static limelight limelight = new limelight();
  public static edu.wpi.first.wpilibj.XboxController xboxcontroller = new XboxController(Constants.xboxcontroller);

  public ActionLists actionList = new ActionLists();
  public TeleopMethods teleopMethods = new TeleopMethods();
  public TestMethods testMethods = new TestMethods();

  private ActionQueue autoActions = new ActionQueue();
  private ActionQueue teleopActions = new ActionQueue();

  @Override
  //Robot does this when waking up
  public void robotInit() {
    SmartDashboard.putString("RobotState", "Robot Disabled");
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
  }

  @Override
  //Robot does this constantly when in "autonomous" mode
  public void autonomousPeriodic() {
    //TODO uncommit once step is fixed

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
    
    if (leftJoystick.getRawButton(1)) {
      teleopMethods.aimForBall();
    }


    /*SmartDashboard.putNumber("Distance", distanceSensor.getDistance());
    SmartDashboard.putBoolean("On Line?", colorSensor.getShade());*/
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


package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Miscellaneous.*;
import frc.robot.RobotMethods.*;
import frc.robot.Subsystems.*;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class Robot extends TimedRobot {
  public static Drive drive = new Drive(Constants.motorBR, Constants.motorFR, Constants.motorBL, Constants.motorFL);
// These declare an instace of a script as a variable and setup the constant talons or other objects.
  public static Joystick rightJoystick = new Joystick(Constants.jstickR);
  public static Joystick leftJoystick = new Joystick(Constants.jstickL);
  public edu.wpi.first.wpilibj.XboxController xboxcontroller = new XboxController(Constants.xboxcontroller);

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
    //autoActions.step();
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

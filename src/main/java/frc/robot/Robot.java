
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Miscellaneous.*;
import frc.robot.RobotMethods.ActionLists;
import frc.robot.RobotMethods.TeleopMethods;
import frc.robot.Subsystems.Drive;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class Robot extends TimedRobot {
  public static final Drive drive = new Drive(Constants.motorBR, Constants.motorFR, Constants.motorBL, Constants.motorFL);
  public static final Joystick rightJoystick = new Joystick(Constants.jstickR);
  public static final Joystick leftJoystick = new Joystick(Constants.jstickL);
  public static final ActionLists actionList = new ActionLists();
  public static final TeleopMethods teleopMethods = new TeleopMethods();

  private final ActionQueue autoActions = new ActionQueue();
  private final ActionQueue teleopActions = new ActionQueue();

  @Override
  public void robotInit() {
    SmartDashboard.putString("RobotState", "Robot Disabled");
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    SmartDashboard.putString("RobotState", "Autonomous");
    actionList.DriveSome(autoActions);
  }

  @Override
  public void autonomousPeriodic() {
    autoActions.step();
  }

  @Override
  public void teleopInit() {
    teleopMethods.init();
  }

  @Override
  public void teleopPeriodic() {
    teleopMethods.drive();
  }

  @Override
  public void disabledInit() {
    SmartDashboard.putString("RobotState", "Robot Disabled");
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {
    SmartDashboard.putString("RobotState", "Testing");
  }

  @Override
  public void testPeriodic() {}
}

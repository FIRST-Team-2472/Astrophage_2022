
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Miscellaneous.*;
import frc.robot.ActionQueue.Runners.ActionQueue;

public class Robot extends TimedRobot {
  public static final Drive drive = new Drive(Constants.motorBR, Constants.motorFR, Constants.motorBL, Constants.motorFL);
  public static final Joystick rightJoystick = new Joystick(Constants.jstickR);
  public static final Joystick leftJoystick = new Joystick(Constants.jstickL);

  private final ActionQueue autoActions = new ActionQueue();

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}

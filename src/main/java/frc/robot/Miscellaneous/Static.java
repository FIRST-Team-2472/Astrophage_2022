package frc.robot.Miscellaneous;

import frc.robot.Robot;

public class Static {
    public static void stopAll() {
        Robot.drive.tankDrivePower(0, 0);
        Robot.intake.runConveyorPower(0);
        Robot.shooter.runFlyWheelPower(0);
        Robot.superClimber.runBothExtendersPower(0, 0);
        Robot.superClimber.runBothRotationsPower(0, 0);
    }
}

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
    private final TalonSRX flyWheel;

    public Shooter(final int FlyWheelID) {
        flyWheel = new TalonSRX(FlyWheelID);
    }

    public void runFlyWheelPower(double speed) {
        flyWheel.set(ControlMode.Velocity, speed);
    }

    public double runSensorVelocity() {
        return flyWheel.getSelectedSensorVelocity();
    }
}
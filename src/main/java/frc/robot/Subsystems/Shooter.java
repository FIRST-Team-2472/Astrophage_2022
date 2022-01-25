package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
    private final TalonSRX flyWheel;

    public Shooter(final int FlyWheelID) {
        flyWheel = new TalonSRX(FlyWheelID);

        // encoders

        flyWheel.setSensorPhase(true); // correct encoder to motor direction

        // Tell the talon that he has a quad encoder
        flyWheel.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

        // Set minimum output (closed loop) to 0 for now
        flyWheel.configNominalOutputForward(0, 30);
        flyWheel.configNominalOutputReverse(0, 30);

        // Set maximum forward and backward to full speed
        flyWheel.configPeakOutputForward(1, 30);
        flyWheel.configPeakOutputReverse(-1, 30);

        // Motion magic cruise (max speed) is 100 counts per 100 ms
        flyWheel.configMotionCruiseVelocity(500, 30);
        flyWheel.configMotionCruiseVelocity(3000, 30);
        flyWheel.configMotionCruiseVelocity(3000, 30);

        // Motion magic acceleration is 50 counts
        flyWheel.configMotionAcceleration(100, 30);

        // Zero the sensor once on robot boot up
        flyWheel.setSelectedSensorPosition(0, 0, 30);
    }

    public void runFlyWheelPower(double speed) {
        flyWheel.set(ControlMode.Velocity, speed);
    }

    public double runSensorVelocity() {
        return flyWheel.getSelectedSensorVelocity();
    }
}
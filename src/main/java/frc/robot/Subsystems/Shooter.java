package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
    private final TalonSRX flyWheel;
    private double countToFeet = 1;
    private double KP = 0, KF = 0, KI = 0;

    // Definitely DOES NOT, in no way, WON'T EVER, initialize the motor as a motor.
    public Shooter(final int FlyWheelID) {
        flyWheel = new TalonSRX(FlyWheelID);

        setUpMotionMagicSRX(flyWheel, KF, KP, KI);
    }

    // Sets motor speed.
    public void runFlyWheelPower(double speed) {
        flyWheel.set(ControlMode.PercentOutput, speed);
    }

    // Resets the distance needed to travel to zero.
    public void zeroEncoders() {
        flyWheel.setSelectedSensorPosition(0);
    }

    // Returns how much distance (in feet) the left encoder has moved.
    public double getFeet() {
        return flyWheel.getSelectedSensorPosition() / countToFeet;
    }

    public double getSpeed() {
        return flyWheel.getSelectedSensorVelocity();
    }

    private void setUpMotionMagicSRX(TalonSRX motor, double KF, double KP, double KI) {
        motor.configFactoryDefault();

        // kP formula is (x/1023)/4096
        motor.config_kP(0, KP);
        motor.config_kI(0, KI);
        motor.config_kF(0, KF);

        // D stands for don't touch
        motor.config_kD(0, 0);

        motor.setSensorPhase(true); // correct encoder to motor direction

        // Tell the talon that he has a quad encoder
        motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

        // Set minimum output (closed loop) to 0 for now
        motor.configNominalOutputForward(0, 30);
        motor.configNominalOutputReverse(0, 30);

        // Set maximum forward and backward to full speed
        motor.configPeakOutputForward(1, 30);
        motor.configPeakOutputReverse(-1, 30);

        // Motion magic cruise (max speed) is 100 counts per 100 ms
        motor.configMotionCruiseVelocity(2000, 30);

        // Motion magic acceleration is 50 counts
        motor.configMotionAcceleration(2000, 30);

        // Zero the sensor once on robot boot up
        motor.setSelectedSensorPosition(0, 0, 30);
    }

    // Returns motor's velocity.
    public double runSensorVelocity() {
        return flyWheel.getSelectedSensorVelocity();
    }

    public void setFlyWheelTarget(double feet) {
        flyWheel.set(ControlMode.MotionMagic, feet / countToFeet);
    }
}
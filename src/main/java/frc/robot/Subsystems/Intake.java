package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake {
    private final TalonSRX conveyor;
    private final TalonSRX frontWheels;
    private final DoubleSolenoid frontWheelPush;

    public Intake(int conveyorID, int frontWheelsID, int pcmID, int frontWheelForwardID, int frontWheelBackID) {
        conveyor = new TalonSRX(conveyorID);
        frontWheels = new TalonSRX(frontWheelsID);
        // Don't know what PnuematicsModuelType does, but it works.
        frontWheelPush = new DoubleSolenoid(pcmID, PneumaticsModuleType.CTREPCM, frontWheelForwardID, frontWheelBackID);
        conveyor.setInverted(true);

        // encoders

        conveyor.setSensorPhase(true); // correct encoder to motor direction

        // Tell the talon that he has a quad encoder
        conveyor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

        // Set minimum output (closed loop) to 0 for now
        conveyor.configNominalOutputForward(0, 30);
        conveyor.configNominalOutputReverse(0, 30);

        // Set maximum forward and backward to full speed
        conveyor.configPeakOutputForward(1, 30);
        conveyor.configPeakOutputReverse(-1, 30);

        // Motion magic cruise (max speed) is 100 counts per 100 ms
        conveyor.configMotionCruiseVelocity(500, 30);
        conveyor.configMotionCruiseVelocity(3000, 30);
        conveyor.configMotionCruiseVelocity(3000, 30);

        // Motion magic acceleration is 50 counts
        conveyor.configMotionAcceleration(100, 30);

        // Zero the sensor once on robot boot up
        conveyor.setSelectedSensorPosition(0, 0, 30);


        /* the other one */

        frontWheels.setSensorPhase(true); // correct encoder to motor direction

        // Tell the talon that he has a quad encoder
        frontWheels.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

        // Set minimum output (closed loop) to 0 for now
        frontWheels.configNominalOutputForward(0, 30);
        frontWheels.configNominalOutputReverse(0, 30);

        // Set maximum forward and backward to full speed
        frontWheels.configPeakOutputForward(1, 30);
        frontWheels.configPeakOutputReverse(-1, 30);

        // Motion magic cruise (max speed) is 100 counts per 100 ms
        frontWheels.configMotionCruiseVelocity(500, 30);
        frontWheels.configMotionCruiseVelocity(3000, 30);
        frontWheels.configMotionCruiseVelocity(3000, 30);

        // Motion magic acceleration is 50 counts
        frontWheels.configMotionAcceleration(100, 30);

        // Zero the sensor once on robot boot up
        frontWheels.setSelectedSensorPosition(0, 0, 30);
    }

    public void runIntake(double speed) {
        runFrontWheels(-speed);
        runConveyorPower(speed);
    }

    public void runFrontWheels(double speed) {
        frontWheels.set(ControlMode.Velocity, speed);
    }

    public void runConveyorPower(double speed) {
        conveyor.set(ControlMode.Velocity, speed);
    }

    public void pushoutfrontwheel() {
        frontWheelPush.set(Value.kForward);
    }

    public void turnOffFrontWheel() {
        frontWheelPush.set(Value.kOff);
    }

    public void pushinfrontwheel() {
        frontWheelPush.set(Value.kReverse);
    }
}
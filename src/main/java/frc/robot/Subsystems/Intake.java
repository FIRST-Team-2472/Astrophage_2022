package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake {
    private final CANSparkMax conveyor;
    private final DoubleSolenoid frontWheelPush;

    public Intake(int conveyorID, int pcmID, int frontWheelForwardID, int frontWheelBackID) {
        conveyor = new CANSparkMax(conveyorID, MotorType.kBrushless);
        // Don't know what PnuematicsModuelType does, but it works.
        frontWheelPush = new DoubleSolenoid(pcmID, PneumaticsModuleType.CTREPCM, frontWheelForwardID, frontWheelBackID);
        conveyor.setInverted(true);



    }

    public void runConveyorPower(double speed) {
        conveyor.set(speed);
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
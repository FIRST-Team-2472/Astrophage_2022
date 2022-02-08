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

    public Intake(int conveyorID) {
        conveyor = new CANSparkMax(conveyorID, MotorType.kBrushless);
        conveyor.setInverted(true);

    }

    public void runConveyorPower(double speed) {
        conveyor.set(speed);
    }
}
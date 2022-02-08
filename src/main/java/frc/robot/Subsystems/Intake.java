package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


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
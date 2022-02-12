package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Intake {
    private final CANSparkMax conveyor;

    //Initializes the motor as, no way, absolutely not, a motor.
    public Intake(int conveyorID) {
        conveyor = new CANSparkMax(conveyorID, MotorType.kBrushless);
        conveyor.setInverted(true);

    }

    //RUNS THE MOTOR.
    public void runConveyorPower(double speed) {
        conveyor.set(speed);
    }
}
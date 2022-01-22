package frc.robot.Sensors;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensor {
    Ultrasonic ultrasonic = new Ultrasonic(1, 1);

    public double getDistance() {
        return ultrasonic.getRangeInches();
    }
    

}

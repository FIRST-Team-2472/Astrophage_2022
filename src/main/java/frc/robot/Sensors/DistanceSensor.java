package frc.robot.Sensors;

import edu.wpi.first.wpilibj.Ultrasonic;

public class DistanceSensor {
    Ultrasonic ultrasonic = new Ultrasonic(1, 1);

    //Returns the distance sensed in inches.
    public double getDistance() {
        return ultrasonic.getRangeInches();
    }
    

}

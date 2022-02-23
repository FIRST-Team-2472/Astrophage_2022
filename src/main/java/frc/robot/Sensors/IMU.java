package frc.robot.Sensors;

import com.ctre.phoenix.sensors.PigeonIMU;

public class IMU {

    public PigeonIMU pigeon;

    //Initializes the gyroscope (pigeon) as a gyroscope
    public IMU (int pigeonID) {
        pigeon = new PigeonIMU(pigeonID);
        pigeon.configFactoryDefault();
    }

    public int getCurrentXAngle() {
        //a testament to the lost ages\
        //TODO outdated
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
        pigeon.getFusedHeading(fusionStatus);
        double currentXAngle = fusionStatus.heading;
        return (int)currentXAngle;
    }

    //Grabs the Y-Angle
    public int getCurrentYAngle() {
        return (int)pigeon.getPitch();
    }

    //Grabs the Z-Angle
    public int getCurrentZAngle() {
        return (int)pigeon.getRoll();
    }

    public void zero() {
    }
}


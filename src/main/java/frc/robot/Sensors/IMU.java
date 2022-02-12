package frc.robot.Sensors;

import com.ctre.phoenix.sensors.PigeonIMU;

public class IMU {

    public PigeonIMU pigeon;

    public IMU (int pigeonID) {
        pigeon = new PigeonIMU(pigeonID);
    }

    public int getCurrentXAngle() {
        //a testament to the lost ages\
        //TODO outdated
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
        pigeon.getFusedHeading(fusionStatus);
        double currentXAngle = fusionStatus.heading;
        return (int)currentXAngle;
    }

    public int getCurrentYAngle() {
        return (int)pigeon.getPitch();
    }

    public int getCurrentZAngle() {
        return (int)pigeon.getRoll();
    }
}


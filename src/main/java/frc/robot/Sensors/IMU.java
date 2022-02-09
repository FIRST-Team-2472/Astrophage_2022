package frc.robot.Sensors;

import com.ctre.phoenix.sensors.PigeonIMU;

public class IMU {

    //TODO be able to find z axis or whichever axis we need

    public PigeonIMU pigeon;

    public IMU (int pigeonID) {
        pigeon = new PigeonIMU(pigeonID);
    }

    public void setUpIMU(){
        PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
    
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();

        double [] xyz_dps = new double [3];

        /* grab some input data from Pigeon and gamepad*/

        pigeon.getGeneralStatus(genStatus);

        pigeon.getRawGyro(xyz_dps);

        pigeon.getFusedHeading(fusionStatus);

        double currentAngle = fusionStatus.heading;

        int yaw = ((int)currentAngle % 360);

        double currentAngularRate = xyz_dps[2];
    }

    public int getCurrentAngle() {
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
        pigeon.getFusedHeading(fusionStatus);
        double currentAngle = fusionStatus.heading;
        return (int)currentAngle;
    }
}


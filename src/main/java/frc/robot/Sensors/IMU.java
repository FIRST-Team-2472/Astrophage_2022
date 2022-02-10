package frc.robot.Sensors;

import com.ctre.phoenix.sensors.PigeonIMU;

public class IMU {

    public PigeonIMU pigeon;

    public IMU (int pigeonID) {
        pigeon = new PigeonIMU(pigeonID);
    }

    public void setUpIMU(){
        PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
    
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();

        double [] xyz_dps = new double [3];

        double [] ypr = new double [3];
        pigeon.getPitch();
        pigeon.getYaw();
        pigeon.getRoll();

        /* grab some input data from Pigeon and gamepad*/

        pigeon.getGeneralStatus(genStatus);

        pigeon.getRawGyro(xyz_dps);

        pigeon.getFusedHeading(fusionStatus);

        double currentXAngle = fusionStatus.heading;

        int yaw = ((int)currentXAngle % 360);

        double currentAngularRate = xyz_dps[2];
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


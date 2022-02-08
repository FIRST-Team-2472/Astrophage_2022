package frc.robot.Sensors;

import com.ctre.phoenix.sensors.PigeonIMU;

public class IMU {

    public PigeonIMU pigeon;

    public void IMU(int pigeonID){
        pigeon = new PigeonIMU(pigeonID);

        PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
    
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();

        double [] xyz_dps = new double [3];

        /* grab some input data from Pigeon and gamepad*/

        pigeon.getGeneralStatus(genStatus);

        pigeon.getRawGyro(xyz_dps);

        pigeon.getFusedHeading(fusionStatus);

        double currentAngle = fusionStatus.heading;

        int yaw = ((int)currentAngle % 360);

        boolean angleIsGood = (pigeon.getState() == PigeonIMU.PigeonState.Ready) ? true : false;

        double currentAngularRate = xyz_dps[2];
    }

    public int getCurrentAngle() {
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
        pigeon.getFusedHeading(fusionStatus);
        double currentAngle = fusionStatus.heading;
        return (int)currentAngle;
    }
}


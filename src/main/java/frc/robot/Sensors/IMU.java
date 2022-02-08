package frc.robot.Sensors;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU {

    public PigeonIMU pigeon = new PigeonIMU(2);

    public void IMU(){
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
    
        SmartDashboard.putBoolean("Pigeon Is Working?", angleIsGood);
        SmartDashboard.putNumber("Angle", yaw);
        SmartDashboard.putNumber("Rate", currentAngularRate);

    }

    public int getCurrentAngle() {
        PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
        pigeon.getFusedHeading(fusionStatus);
        double currentAngle = fusionStatus.heading;
        return (int)currentAngle;
    }
}


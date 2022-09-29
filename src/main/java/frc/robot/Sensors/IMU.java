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
        return (int)pigeon.getYaw();
    }

    //Grabs the Y-Angle
    public int getCurrentYAngle() {
        return (int)pigeon.getPitch();
    }

    //Grabs the Z-Angle
    public int getCurrentZAngle() {
        return (int)pigeon.getRoll();
    }

    public int getRealXAngle() {
        int temp = getCurrentXAngle()%360;

        if (temp >= 0)
            return temp;
        else 
            return 360+temp;
    }

    public void zero() {
        pigeon.setYaw(0);
    }
}


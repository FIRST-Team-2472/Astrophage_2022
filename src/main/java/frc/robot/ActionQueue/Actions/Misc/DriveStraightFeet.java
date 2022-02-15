package frc.robot.ActionQueue.Actions.Misc;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class DriveStraightFeet implements Actionable {

    double feet;

    public DriveStraightFeet(double feet) 
    {
        this.feet = feet;
    }

    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Drive Feet");
        Robot.drive.driveFeet(feet);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void endAction() 
    {

    }

    @Override
    public boolean isFinished(){
        if(Math.abs(Robot.drive.getLeftFeet() - feet) < 2 || Math.abs(Robot.drive.getRightFeet() - feet) < 2) return true;
        else return false;
    }

}

package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class AlignClimber implements Actionable{
    double find90 = 15 * Robot.superClimber.encoderToDegrees;

    public AlignClimber() {
        
        //bout to catch me a big fish
    }
    
    @Override
    public void startAction() 
    {
        //TODO needs motion magic
        SmartDashboard.putString("ActionName", "Straghting the climber");
    }



    @Override
    public void periodic() 
    {
        if (Robot.superClimber.getRotationLAngle() < find90) 
            Robot.superClimber.runRotationL(.3);
        else if (Robot.superClimber.getRotationLAngle() > find90) 
            Robot.superClimber.runRotationL(-.3);
        else if (Math.abs(find90 - Robot.superClimber.getRotationLAngle()) < 1)  
            Robot.superClimber.runRotationL(0);
        
        if (Robot.superClimber.getRotationRAngle() < find90)
            Robot.superClimber.runRotationR(0.3);
        else if (Robot.superClimber.getRotationRAngle() > find90) 
            Robot.superClimber.runRotationR(0.3);
        else if (Math.abs(find90 - Robot.superClimber.getRotationRAngle()) < 1)
            Robot.superClimber.runRotationR(0);
    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothExtenders(0);
    }

    @Override
    public boolean isFinished()
    {
        if (Math.abs(Robot.superClimber.getRotationLAngle() - find90) <= 1 && Math.abs(Robot.superClimber.getRotationRAngle() - find90) <= 1) return true;
        else return false;
    }

}


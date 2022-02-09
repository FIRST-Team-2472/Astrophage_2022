package frc.robot.ActionQueue.Actions.Climbing;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class AlignClimber implements Actionable{

    public AlignClimber() {
        
        //bout to catch me a big fish
    }
    
    @Override
    public void startAction() 
    {
        SmartDashboard.putString("ActionName", "Reeling her in");
        Robot.superClimber.runBothExtendo(-0.3);
    }



    @Override
    public void periodic() 
    {

    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothExtendo(0);
    }

    @Override
    public boolean isFinished()
    {
        //TODO fix max not wanting to do accual work
        /*if(someunspeffiedlimitswtichison) return true;
        else return false;*/
        return false;
    }

}


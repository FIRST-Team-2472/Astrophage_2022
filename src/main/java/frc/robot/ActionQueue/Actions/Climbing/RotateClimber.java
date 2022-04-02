package frc.robot.ActionQueue.Actions.Climbing;

import frc.robot.Robot;
import frc.robot.ActionQueue.Runners.Actionable;

public class RotateClimber implements Actionable {
    
    public double angle;
    public boolean forward, zeroGo = false;

    public RotateClimber (double angle) {
        this.angle = angle;
        if(angle == 0) zeroGo = true;
    }
    
    @Override
    public void startAction() {
        if (angle > Robot.superClimber.getRotationRAngle()) forward = true;
        else forward = false;
    }

    @Override
    public void periodic() 
    {
        double correction = -(Robot.superClimber.getRotationLAngle() - Robot.superClimber.getRotationRAngle()) *0.0000007;

        if (forward) {
            if (angle >= Robot.superClimber.getRotationLAngle()) {
                if (zeroGo) {
                    if (!Robot.superClimber.isLeftVertical())
                        Robot.superClimber.runRotationPowerL(.7);
                    else
                        Robot.superClimber.runRotationPowerL(0);
                }
                else 
                    Robot.superClimber.runRotationPowerL(.7);
            }
            else 
                Robot.superClimber.runRotationPowerL(0);
            
            if (angle >= Robot.superClimber.getRotationRAngle()) {
                if (zeroGo) {
                    if (!Robot.superClimber.isRightVertical())
                        Robot.superClimber.runRotationPowerR(.7+correction);
                    else
                        Robot.superClimber.runRotationPowerR(0);
                }
                else 
                    Robot.superClimber.runRotationPowerR(.7+correction);
            }
            else 
                Robot.superClimber.runRotationPowerR(0);
        }
        else {
            if (angle <= Robot.superClimber.getRotationLAngle()) {
                if (zeroGo) {
                    if (!Robot.superClimber.isLeftVertical())
                        Robot.superClimber.runRotationPowerL(-.7);
                    else
                        Robot.superClimber.runRotationPowerL(0);
                }
                else 
                    Robot.superClimber.runRotationPowerL(-.7);
            }
            else 
                Robot.superClimber.runRotationPowerL(0);

            if (angle <= Robot.superClimber.getRotationRAngle()) {
                if (zeroGo) {
                    if (!Robot.superClimber.isRightVertical())
                        Robot.superClimber.runRotationPowerR(-.7+correction);
                    else
                        Robot.superClimber.runRotationPowerR(0);
                }
                else 
                    Robot.superClimber.runRotationPowerR(-.7+correction);
            }
            else 
                Robot.superClimber.runRotationPowerR(0);
        }
    }

    @Override
    public void endAction() 
    {
        Robot.superClimber.runBothRotations(0);
    }

    @Override
    public boolean isFinished()
    {
        if (forward) {
            if (zeroGo) {
                if ((angle <= Robot.superClimber.getRotationLAngle() && angle <= Robot.superClimber.getRotationRAngle()) || Robot.superClimber.isVertical()) return true;
                else return false;
            }
            else {
                if (angle <= Robot.superClimber.getRotationLAngle() && angle <= Robot.superClimber.getRotationRAngle()) return true;
                else return false;
            }
        } else {
            if (zeroGo) {
                if ((angle >= Robot.superClimber.getRotationLAngle() && angle >= Robot.superClimber.getRotationRAngle()) || Robot.superClimber.isVertical()) return true;
                else return false;
            }
            else {
                if (angle >= Robot.superClimber.getRotationLAngle() && angle >= Robot.superClimber.getRotationRAngle()) return true;
                else return false;
            }
        }
    }
}


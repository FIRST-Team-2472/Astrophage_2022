package frc.robot.Sensors;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;


public class ColorSensor {
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();


    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    RawColor colorSensorV3 = m_colorSensor.getRawColor();

    
    public void getShade() {
    if (((m_colorSensor.getRed() + m_colorSensor.getBlue() + m_colorSensor.getGreen()) / 3) < 12) {
      System.out.println("Ready to Climb");
      SmartDashboard.putBoolean("Seeing Black?", true);
    }
    else {
      System.out.println("Not Ready to Climb");
      SmartDashboard.putBoolean("Seeing Black?", false);
    }
}
}

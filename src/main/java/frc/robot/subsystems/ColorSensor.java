package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {
    
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color kYellowTarget = ColorMatch.makeColor(0.380, 0.502, 0.122);
    private final Color kRedTarget = ColorMatch.makeColor(0.502, 0.365, 0.137);
    private final Color kGreenTarget = ColorMatch.makeColor(0.224, 0.549, 0.235);
    private final Color kBlueTarget = ColorMatch.makeColor(0.208, 0.471, 0.345);
    public String color_str;
    public int proximity;

    public ColorSensor() {
        m_colorMatcher.addColorMatch(kYellowTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kBlueTarget);
    }

    @Override
    public void periodic() {
        Color color = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(color);

        if (match.color == kYellowTarget) {
          color_str = "Yellow";
        } else if (match.color == kRedTarget) {
          color_str = "Red";
        } else if (match.color == kGreenTarget) {
          color_str = "Green";
        } else if (match.color == kBlueTarget) {
          color_str = "Blue";
        } else {
          color_str = "Unknown";
        }

        proximity = m_colorSensor.getProximity();
        SmartDashboard.putNumber("Proximity", Math.ceil(proximity));
        SmartDashboard.putNumber("Red", Math.ceil(color.red * 255));
        SmartDashboard.putNumber("Green", Math.ceil(color.green * 255));
        SmartDashboard.putNumber("Blue", Math.ceil(color.blue * 255));
        SmartDashboard.putString("Detected Color", color_str);
        SmartDashboard.putNumber("Confidence", Math.ceil(match.confidence * 100));
    }
}

package frc.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ColorSensor extends SubsystemBase {
    
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kYellowTarget = ColorMatch.makeColor(0.380, 0.502, 0.122);
  private final Color kRedTarget = ColorMatch.makeColor(0.502, 0.365, 0.137);
  private final Color kGreenTarget = ColorMatch.makeColor(0.224, 0.549, 0.235);
  private final Color kBlueTarget = ColorMatch.makeColor(0.208, 0.471, 0.345);
  public String color_str, game_data, color_required = "", color_start = "", color_previous = "";
  public int proximity, rotations = 0;
  public boolean active_match = false, active_rotate = false;

  public ColorSensor() {
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kBlueTarget);
    SmartDashboard.putString("Required Color", "");
    SmartDashboard.putNumber("Rotations", rotations);
  }

  public void stopWheel() {
    RobotContainer.m_pwm.setWheel(-0.3);
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        RobotContainer.m_pwm.setWheel(0.0);
      }
    }, 750);
  }

  public void color_match() {
    if (!color_required.isEmpty()) {
      System.out.println("Matching color to " + color_required + "...");
      RobotContainer.m_pwm.setWheel(0.3);
      active_match = true;
    }
  }

  public void color_rotate() {
    rotations = 0;
    System.out.println("Rotating wheel four times...");
    color_start = color_str;
    RobotContainer.m_pwm.setWheel(0.3);
    active_rotate = true;
  }

  @Override
  public void periodic() {
    Color color = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(color);

    if (match.color == kYellowTarget) {
      color_str = "Y";
    } else if (match.color == kRedTarget) {
      color_str = "R";
    } else if (match.color == kGreenTarget) {
      color_str = "G";
    } else if (match.color == kBlueTarget) {
    color_str = "B";
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

    String game_data_temp = DriverStation.getInstance().getGameSpecificMessage();
    if (!game_data_temp.equals(game_data)) {
      System.out.println("Got new game data: " + game_data_temp);
      game_data = game_data_temp;
      switch (game_data) {
        case "Y":
          color_required = "G";
          break;
        case "R":
          color_required = "B";
          break;
        case "G":
          color_required = "Y";
          break;
        case "B":
          color_required = "R";
          break;
        default:
          System.out.println("Game data is either empty or corrupted!");
          break;
      }
      SmartDashboard.putString("Required Color", color_required);
    }

    if (active_match && color_str.equalsIgnoreCase(color_required)) {
      stopWheel();
      active_match = false;
    }

    if (active_rotate && color_str.equalsIgnoreCase(color_start) && !color_str.equalsIgnoreCase(color_previous)) {
      rotations++;
      SmartDashboard.putNumber("Rotations", rotations);
      if (rotations == 4) {
        stopWheel();
        active_rotate = false;
      }
    }

    color_previous = color_str;
  }
}

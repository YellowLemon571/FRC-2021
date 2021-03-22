package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.PWM;

public class Wheel extends CommandBase {
    
    public Wheel() {
        addRequirements(RobotContainer.m_colorSensor);
    }

    @Override
    public void initialize() {
        if (PWM.state_arm) {
            String game_data = DriverStation.getInstance().getGameSpecificMessage();
            if (game_data.isEmpty()) {
                RobotContainer.m_colorSensor.color_rotate();
            } else {
                RobotContainer.m_colorSensor.color_match();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

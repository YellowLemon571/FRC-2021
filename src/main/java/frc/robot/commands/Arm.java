package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.PWM;

public class Arm extends CommandBase {

    public Arm() {
        addRequirements(RobotContainer.m_pwm, RobotContainer.m_colorSensor);
    }

    @Override
    public void initialize() {
        RobotContainer.m_pwm.setArm();
        if ((RobotContainer.m_colorSensor.active_match || RobotContainer.m_colorSensor.active_rotate) && PWM.state_arm) {
            RobotContainer.m_colorSensor.stopWheel();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

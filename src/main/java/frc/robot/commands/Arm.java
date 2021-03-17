package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Arm extends CommandBase {

    public Arm() {
        addRequirements(RobotContainer.m_pwm);
    }

    @Override
    public void initialize() {
        RobotContainer.m_pwm.setArm();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

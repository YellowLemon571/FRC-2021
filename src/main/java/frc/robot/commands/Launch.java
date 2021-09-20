package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Launch extends CommandBase {
    
    private String s;

    public Launch(String s) {
        addRequirements(RobotContainer.m_pwm);
        this.s = s;
    }

    @Override
    public void initialize() {
        if (s.equalsIgnoreCase("stop") && RobotContainer.m_pwm.state_feed) RobotContainer.m_pwm.setFeed(false);
        RobotContainer.m_pwm.setLaunch(s);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

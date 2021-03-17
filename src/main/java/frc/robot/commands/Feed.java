package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Feed extends CommandBase {
    
    private boolean b;

    public Feed(boolean b) {
        addRequirements(RobotContainer.m_pwm);
        this.b = b;
    }

    @Override
    public void initialize() {
        if (RobotContainer.m_pwm.state_launch) RobotContainer.m_pwm.setFeed(b);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

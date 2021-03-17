package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Launch extends CommandBase {
    
    private boolean b;

    public Launch(boolean b) {
        addRequirements(RobotContainer.m_pwm);
        this.b = b;
    }

    @Override
    public void initialize() {
        if (!b && RobotContainer.m_pwm.state_feed) RobotContainer.m_pwm.setFeed(false);
        RobotContainer.m_pwm.setLaunch(b);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

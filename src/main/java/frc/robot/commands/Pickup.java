package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Pickup extends CommandBase {
    
    private boolean b;

    public Pickup(boolean b) {
        addRequirements(RobotContainer.m_pwm);
        this.b = b;
    }

    @Override
    public void initialize() {
        RobotContainer.m_pwm.setPickup(b);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

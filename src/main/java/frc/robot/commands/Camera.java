package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Camera extends CommandBase {
    
    private int state = 0;

    public Camera() {
        addRequirements(RobotContainer.m_cameras);
    }

    @Override
    public void initialize() {
        RobotContainer.m_cameras.setCamera((state == 0) ? 1 : 0);
        state = (state == 0) ? 1 : 0;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

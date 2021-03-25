package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AdjustSpeed extends CommandBase {
    
    private boolean b;

    public AdjustSpeed(boolean b) {
        addRequirements(RobotContainer.m_pwm);
        this.b = b;
    }

    @Override
    public void initialize() {
        if (b && RobotContainer.m_pwm.speed_launch <= 1.0) {
            RobotContainer.m_pwm.speed_launch += 0.025;

        } else if (!b && RobotContainer.m_pwm.speed_launch >= 0.5) {
            RobotContainer.m_pwm.speed_launch -= 0.025;
        }
        SmartDashboard.putNumber("Launch Speed", RobotContainer.m_pwm.speed_launch);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

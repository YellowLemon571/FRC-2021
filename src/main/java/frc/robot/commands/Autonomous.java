package frc.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Autonomous extends CommandBase {
    
    private String active = "stop";
    private Timer timer;

    private final TimerTask tt_stop = new TimerTask() {
        @Override
        public void run() {
            active = "stop";
        }
    };

    private final TimerTask tt_reverse = new TimerTask() {
        @Override
        public void run() {
            active = "reverse";
            timer.schedule(tt_stop, 150);
        }
    };

    public Autonomous() {
        addRequirements(RobotContainer.m_pwm);
        timer = new Timer();
    }

    @Override
    public void initialize() {
        active = "forward";
        RobotContainer.m_pwm.setPickup(true);
        timer.schedule(tt_reverse, 400);
    }

    @Override
    public void execute() {
        if (active.equalsIgnoreCase("forward")) {
            Robot.differentialDrive.arcadeDrive(1.0, 0.0);
        } else if (active.equalsIgnoreCase("reverse")) {
            Robot.differentialDrive.arcadeDrive(-0.2, 0.2);
        } else {
            Robot.differentialDrive.arcadeDrive(0.0, 0.0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean isInterrupted) {
        RobotContainer.m_pwm.setPickup(false);
        Robot.differentialDrive.stopMotor();
    }
}

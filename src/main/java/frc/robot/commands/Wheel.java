package frc.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Wheel extends CommandBase {

    private String active;
    private String active_prev;
    
    public Wheel(String s) {
        addRequirements(RobotContainer.m_pwm);
        this.active = s;
    }

    @Override
    public void initialize() {
        if (active.equalsIgnoreCase("forward") && RobotContainer.m_pwm.state_arm) {
            RobotContainer.m_pwm.setWheel(0.4);
        } else if (active.equalsIgnoreCase("reverse")) {
            RobotContainer.m_pwm.setWheel(-0.4);
        } else if (active.equalsIgnoreCase("stop")) {
            RobotContainer.m_pwm.setWheel(active_prev.equalsIgnoreCase("forward") ? -0.3 : 0.3);
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    RobotContainer.m_pwm.setWheel(0.0);
                }
            }, 300);
        }
        active_prev = active;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DIO extends SubsystemBase {

    public static DigitalInput arm_up, arm_down;
    public boolean state_arm = false;

    public DIO() {
        arm_up = new DigitalInput(Constants.DIO_ARM[0]);
        arm_down = new DigitalInput(Constants.DIO_ARM[1]);
    }

    @Override
    public void periodic() {
        if (arm_up.get() && !state_arm) {
            RobotContainer.m_pwm.stopArm(true);
            state_arm = true;
        } else if (arm_down.get() && state_arm) {
            RobotContainer.m_pwm.stopArm(false);
            state_arm = false;
        }
        SmartDashboard.putString("Arm Position", state_arm ? "Up" : "Down");
    }    
}

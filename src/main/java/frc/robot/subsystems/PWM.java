package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PWM extends SubsystemBase {
    
    private static PWMVictorSPX spx_feed_left, spx_feed_right, spx_launch, spx_pickup, spx_arm, spx_wheel;
    public boolean state_feed = false, state_launch = false, state_pickup = false;
    public static boolean state_arm = false;
    public double speed_launch = 0.75;

    public PWM() {
        spx_feed_left = new PWMVictorSPX(Constants.PWM_FEED[0]);
        spx_feed_right = new PWMVictorSPX(Constants.PWM_FEED[1]);
        spx_launch = new PWMVictorSPX(Constants.PWM_LAUNCH);
        spx_pickup = new PWMVictorSPX(Constants.PWM_PICKUP);
        spx_arm = new PWMVictorSPX(Constants.PWM_ARM);
        spx_wheel = new PWMVictorSPX(Constants.PWM_WHEEL);
        SmartDashboard.putBoolean("Arm Active", false);
        SmartDashboard.putNumber("Launch Speed", 0.75);
    }

    public void setFeed(boolean b) {
        double speed_left = b ? -0.20 : 0.0;
        double speed_right = b ? 0.75 : 0.0;
        spx_feed_left.set(speed_left);
        spx_feed_right.set(speed_right);
        state_feed = b;
    }

    public void setLaunch(boolean b) {
        double speed = b ? -speed_launch : 0.0;
        spx_launch.set(speed);
        state_launch = b;
    }

    public void setPickup(boolean b) {
        double speed = b ? 0.5 : 0.0;
        spx_pickup.set(speed);
        state_pickup = b;
    }

    public void setArm() {
        if (state_arm && !DIO.arm_down.get()) {
            spx_arm.set(-0.3);
            SmartDashboard.putBoolean("Arm Active", true);
        } else if (!state_arm && !DIO.arm_up.get()) {
            spx_arm.set(0.5);
            SmartDashboard.putBoolean("Arm Active", true);
        }
    }

    public static void stopArm(boolean b) {
        spx_arm.set(0.0);
        SmartDashboard.putBoolean("Arm Active", false);
        state_arm = b;
    }

    public void setWheel(double d) {
        spx_wheel.set(d);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Feed", state_feed);
        SmartDashboard.putBoolean("Launch", state_launch);
        SmartDashboard.putBoolean("Pickup", state_pickup);
    }
}

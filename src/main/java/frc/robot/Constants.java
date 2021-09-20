// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // PWM
    public static final int[] PWM_DRIVE = {0, 1};
    public static final int[] PWM_FEED = {2, 3};
    public static final int PWM_LAUNCH = 4;
    public static final int PWM_PICKUP = 5;
    public static final int PWM_ARM = 6;
    public static final int PWM_WHEEL = 7;

    // KONO DIO DA!
    public static final int[] DIO_ARM = {0, 1};

    // Controller
    public static final int CTRL_0 = 0;
    public static final int CTRL_1 = 1;
    public static final int CTRL0_LY = 1;
    public static final int CTRL0_RX = 2;
    public static final int CTRL0_LB = 5;
    public static final int CTRL0_START = 10;
    public static final int CTRL1_TSIDE = 2;
    public static final int CTRL1_Z = 1;
    public static final int CTRL1_DIAL = 3;
    public static final int CTRL1_TBR = 4;
    public static final int CTRL1_TTR = 6;
    public static final int CTRL1_TTL = 5;
    public static final int CTRL1_S6 = 11;
    public static final int CTRL1_S3 = 12;
}

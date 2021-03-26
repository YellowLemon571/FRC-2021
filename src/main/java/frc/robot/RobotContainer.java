// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Arm;
import frc.robot.commands.Camera;
import frc.robot.commands.Feed;
import frc.robot.commands.Launch;
import frc.robot.commands.Pickup;
import frc.robot.commands.Wheel;
import frc.robot.subsystems.Cameras;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DIO;
import frc.robot.subsystems.PWM;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static final PWM m_pwm = new PWM();
  public static final DIO m_dio = new DIO();
  public static final ColorSensor m_colorSensor = new ColorSensor();
  public static final Cameras m_cameras = new Cameras();
  public final Joystick joystick_0 = new Joystick(Constants.CTRL_0);
  public static final Joystick joystick_1 = new Joystick(Constants.CTRL_1);
  public final Button btn_tside = new JoystickButton(joystick_1, Constants.CTRL1_TSIDE);
  public final Button btn_z = new JoystickButton(joystick_1, Constants.CTRL1_Z);
  public final Button btn_lb = new JoystickButton(joystick_0, Constants.CTRL0_LB);
  public final Button btn_thumbbr = new JoystickButton(joystick_1, Constants.CTRL1_TBR);
  public final Button btn_start = new JoystickButton(joystick_0, Constants.CTRL0_START);
  public final Button btn_thumbtr = new JoystickButton(joystick_1, Constants.CTRL1_TTR);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    btn_z.whenPressed(new Feed(true));
    btn_z.whenReleased(new Feed(false));
    btn_tside.whenPressed(new Launch(true));
    btn_tside.whenReleased(new Launch(false));
    btn_lb.whenPressed(new Pickup(true));
    btn_lb.whenReleased(new Pickup(false));
    btn_thumbbr.whenPressed(new Arm());
    btn_start.whenPressed(new Camera());
    btn_thumbtr.whenPressed(new Wheel());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}

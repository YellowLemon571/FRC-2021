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
import frc.robot.commands.Feed;
import frc.robot.commands.Launch;
import frc.robot.commands.Pickup;
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
  public final Joystick joystick = new Joystick(Constants.CTRL);
  public final Button btn_rt = new JoystickButton(joystick, Constants.CTRL_RT);
  public final Button btn_lt = new JoystickButton(joystick, Constants.CTRL_LT);
  public final Button btn_a = new JoystickButton(joystick, Constants.CTRL_A);

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
    btn_rt.whenPressed(new Feed(true));
    btn_rt.whenReleased(new Feed(false));
    btn_lt.whenPressed(new Launch(true));
    btn_lt.whenReleased(new Launch(false));
    btn_a.whenPressed(new Pickup(true));
    btn_a.whenReleased(new Pickup(false));
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

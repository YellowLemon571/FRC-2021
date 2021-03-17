// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private PWMVictorSPX spx_left, spx_right;
  private DifferentialDrive differentialDrive;
  private UsbCamera camera;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    spx_left = new PWMVictorSPX(Constants.PWM_DRIVE[0]);
    spx_right = new PWMVictorSPX(Constants.PWM_DRIVE[1]);
    differentialDrive = new DifferentialDrive(spx_left, spx_right);
    SmartDashboard.putNumber("Speed", 0);
    SmartDashboard.putNumber("Rotation", 0);

    camera = CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    differentialDrive.arcadeDrive(0, 0);
    SmartDashboard.putNumber("Speed", 0);
    SmartDashboard.putNumber("Rotation", 0);
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double xSpeed = -m_robotContainer.joystick.getRawAxis(Constants.CTRL_LY) * 0.7;
    double zRotation = m_robotContainer.joystick.getRawAxis(Constants.CTRL_RX) * 0.5;
    int pov = m_robotContainer.joystick.getPOV();
    if (pov == 0) {
      xSpeed = 0.5;
      zRotation = 0.0;
    } else if (pov == 90) {
      xSpeed = 0.0;
      zRotation = 0.5;
    } else if (pov == 180) {
      xSpeed = -0.5;
      zRotation = 0.0;
    } else if (pov == 270) {
      xSpeed = 0.0;
      zRotation = -0.5;
    }
    differentialDrive.arcadeDrive(xSpeed, zRotation);
    SmartDashboard.putNumber("Speed", xSpeed);
    SmartDashboard.putNumber("Rotation", zRotation);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}

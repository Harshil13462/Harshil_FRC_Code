// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DrivePID;
import frc.robot.commands.IntakeTimed;
import frc.robot.commands.ShooterDistance;
import frc.robot.commands.ShooterTimed;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain;
  private final DriveForwardTimed driveForwardTimed;
  private final DrivePID drivePID;
  private final Shooter shooter;
  private final ShooterDistance shootDistance;
  private final ShooterTimed shootTimed;
  private final Intake intake;
  private final IntakeTimed intakeTimed;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    driveTrain = new DriveTrain();

    driveForwardTimed = new DriveForwardTimed(driveTrain);
    driveForwardTimed.addRequirements(driveTrain);

    drivePID = new DrivePID(driveTrain);
    drivePID.addRequirements(driveTrain);
    
    shooter = new Shooter();
    
    shootDistance = new ShooterDistance(shooter);
    shootDistance.addRequirements(shooter);

    shootTimed = new ShooterTimed(shooter);
    shootTimed.addRequirements(shooter);

    intake = new Intake();
    intakeTimed = new IntakeTimed(intake);
    intakeTimed.addRequirements(intake);
    intake.setDefaultCommand(intakeTimed);
    
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveForwardTimed;
  }
}

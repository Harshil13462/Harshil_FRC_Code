// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import java.lang.Math;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Declaring all of the variables that will be used in the code
  private Command m_autonomousCommand;
  double error;
  double prev_error;
  double speed;
  double rate_of_change;
  private final CANSparkMax m_leftDrive = new CANSparkMax(7, MotorType.kBrushless);
  private final CANSparkMax m_rightDrive = new CANSparkMax(5, MotorType.kBrushless);
  private final Joystick m_stick = new Joystick(0);
  private final RelativeEncoder m_encoder = m_leftDrive.getEncoder();
  private final Timer m_timer = new Timer();
  double prev_speed;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    // sets the variables to their required values and puts the robot on Coast mode so it doesnt change speed to fast
    m_rightDrive.setInverted(true);
    m_leftDrive.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_rightDrive.setIdleMode(CANSparkMax.IdleMode.kCoast);

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
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
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    // initializes timer/encoders
    m_timer.reset();
    m_timer.start();
    m_encoder.setPosition(0);
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // repeats until the encoder reaches the desired value
    if (Math.abs(m_encoder.getPosition()) < Math.abs(Constants.distance)) {
      // evaluates error and rate of change
      error = (Constants.distance / 36 - m_encoder.getPosition() / 36);
      rate_of_change = (prev_error - error) / 20;
      
      // makes sure that the speed doesn't increase/decrease too fast
      prev_speed = speed;
      speed = Constants.KP * error + rate_of_change * Constants.KD;
      if (speed - prev_speed > Constants.MAX_ROC) {
        speed = prev_speed + Constants.MAX_ROC;
      }
      if (speed - prev_speed < -1 * Constants.MAX_ROC) {
        speed = prev_speed - Constants.MAX_ROC;
      }

      // Makes sure that the robot doesn't move too fast
      if (Math.abs(speed) > Constants.MAX_SPEED) {
        if (speed > 0) {
          speed = Constants.MAX_SPEED;
        }
        else {
          speed = -1 * Constants.MAX_SPEED;
        }
      }

      // Sets the speeds
      m_leftDrive.set(speed);
      m_rightDrive.set(speed);
      
      
      SmartDashboard.putNumber("Percentage: ", m_encoder.getPosition() / Constants.distance); // Displays the robot info on the Dashboard
      
      prev_error = error; // Saves this error value for use in the next iteration
    }
    else {
      // stops the robot as soon as it reaches the target
      m_leftDrive.set(0);
      m_rightDrive.set(0);
    }
  }

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
    m_leftDrive.set(-1 * (m_stick.getY() + m_stick.getX()));
    m_rightDrive.set(-1 * (m_stick.getY() - m_stick.getX()));
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}

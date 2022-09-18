// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
  public final CANSparkMax m_leftDrive = new CANSparkMax(Constants.LEFT_DRIVE, MotorType.kBrushless);
  public final CANSparkMax m_rightDrive = new CANSparkMax(Constants.RIGHT_DRIVE, MotorType.kBrushless);
  public final Joystick m_stick = new Joystick(Constants.JOYSTICK_MOTOR);
  public final Timer m_timer = new Timer();
  public final RelativeEncoder m_encoder = m_leftDrive.getEncoder();
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    m_rightDrive.setInverted(true);
    m_leftDrive.setInverted(false);
    m_timer.reset();
    m_timer.start();
  }
  public void DriveForward(double speed) {
    m_rightDrive.set(speed);
    m_leftDrive.set(speed);
  }
  public void stop() {
    m_rightDrive.set(0);
    m_leftDrive.set(0);
  }
  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
}

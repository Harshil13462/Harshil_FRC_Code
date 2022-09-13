// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final CANSparkMax m_leftDrive = new CANSparkMax(7, MotorType.kBrushless);
  private final CANSparkMax m_rightDrive = new CANSparkMax(5, MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_leftDrive.getEncoder();
  // SpeedControllerGroup leftMotors;
  // SpeedControllerGroup rightMotors;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveForward(double speed) {
    m_leftDrive.set(speed);
    m_rightDrive.set(speed);
  }
  
  public double getEncoder() {
    return m_encoder.getPosition();
  }

  public boolean driveToDistance(double setPointDistance, double speed) {
    return true;
  }

  public void stop() {
    m_leftDrive.set(0);
    m_rightDrive.set(0);
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  CANSparkMax shooter;
  RelativeEncoder shooter_encoder;
  public Shooter() {
    CANSparkMax shooter = new CANSparkMax(Constants.SHOOTER, MotorType.kBrushless);
    RelativeEncoder shooter_encoder = shooter.getEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setShooterSpeed(double speed) {
    shooter.set(speed);
  }

  public void stopShooter() {
    shooter.set(0);
  }

  public double getShooterPosition() {
    return shooter_encoder.getPosition();
  }

  public void resetShooterPosition() {
    shooter_encoder.setPosition(0);
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final WPI_TalonFX rightFront = new WPI_TalonFX(1);
  private final WPI_TalonFX rightBack = new WPI_TalonFX(2);
  private final WPI_TalonFX leftFront = new WPI_TalonFX(3);
  private final WPI_TalonFX leftBack = new WPI_TalonFX(4);

  private MotorControllerGroup rightMotors = new MotorControllerGroup(rightFront, rightBack);
  private MotorControllerGroup leftMotors = new MotorControllerGroup(leftFront, leftBack);  
  private DifferentialDrive drive = new DifferentialDrive(rightMotors, leftMotors);

  // SpeedControllerGroup leftMotors;
  // SpeedControllerGroup rightMotors;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    rightFront.configFactoryDefault();
    leftFront.configFactoryDefault();
    rightBack.configFactoryDefault();
    leftBack.configFactoryDefault();

    rightFront.setNeutralMode(NeutralMode.Brake);
    leftFront.setNeutralMode(NeutralMode.Brake);
    rightBack.setNeutralMode(NeutralMode.Brake);
    leftBack.setNeutralMode(NeutralMode.Brake);

    rightBack.follow(rightFront);
    leftBack.follow(leftFront);

    rightMotors.setInverted(false);
    leftMotors.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double speed, double rotation, double power) {
    drive.arcadeDrive(speed * power, rotation * power);
  }

  public void tankDrive(double left, double right, double power) {
    drive.tankDrive(left * power, right * power);
  }

  public void stop() {
    drive.stopMotor();
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final CANSparkMax m_leftDrive = new CANSparkMax(7, MotorType.kBrushless);
  private final CANSparkMax m_rightDrive = new CANSparkMax(5, MotorType.kBrushless);
  // SpeedControllerGroup leftMotors;
  // SpeedControllerGroup rightMotors;
  DifferentialDrive drive;
  private final AnalogInput rangeFinder;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    rangeFinder = new AnalogInput(Constants.RANGE_FINDER);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveWithJoysticks(XboxController controller, double speed) {
    drive.arcadeDrive(controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS) * speed, controller.getRawAxis(Constants.XBOX_LEFT_X_AXIS) * speed);
  }

  public void driveForward(double speed) {
    drive.tankDrive(speed, speed);
  }

  public boolean driveToDistance(double setPointDistance, double speed) {
    while (rangeFinder.getAverageVoltage() > setPointDistance) {
      driveForward(speed);
    }
    return true;
  }

  public void stop() {
    drive.stopMotor();
  }
}
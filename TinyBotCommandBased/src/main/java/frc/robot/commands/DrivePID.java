// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DrivePID extends CommandBase {
  private final DriveTrain dt;
  double error, rate_of_change, prev_error,prev_speed, speed;
  /** Creates a new DrivePID. */
  public DrivePID(DriveTrain driveTrain) {
    dt = driveTrain;
    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dt.m_encoder.setPosition(0);
    // repeats until the encoder reaches the desired value
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(dt.m_encoder.getPosition()) < Math.abs(Constants.distance)) {
      // evaluates error and rate of change
      error = (Constants.distance / 36 - dt.m_encoder.getPosition() / 36);
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
      dt.DriveForward(speed);
      
      
      SmartDashboard.putNumber("Harshil Percentage: ", dt.m_encoder.getPosition() / Constants.distance);
      
      prev_error = error;
    }
    else {
      dt.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

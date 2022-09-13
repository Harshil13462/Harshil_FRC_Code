// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DrivePID extends CommandBase {
  DriveTrain driveTrain;
  double error;
  double prev_error;
  double speed;
  double rate_of_change;
  double prev_speed;
  

  /** Creates a new DrivePID. */
  public DrivePID(DriveTrain dt) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = dt;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(driveTrain.getEncoder()) < Math.abs(Constants.distance)) {
      // evaluates error and rate of change
      error = (Constants.distance / 36 - driveTrain.getEncoder() / 36);
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
      driveTrain.driveForward(speed);
      
      SmartDashboard.putNumber("Percentage: ", driveTrain.getEncoder() / Constants.distance); // Displays the robot info on the Dashboard
      
      prev_error = error; // Saves this error value for use in the next iteration
    }
    else {
      // stops the robot as soon as it reaches the target
      driveTrain.stop();
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

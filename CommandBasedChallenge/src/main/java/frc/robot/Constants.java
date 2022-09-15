// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Drivetrain
    public static final double AUTONOMOUS_SPEED = 0.4;
    public static final double DRIVE_FORWARD_TIME = 3.0;
    public static final double KP = 1; // KP Constant Value
    public static final double distance = -216; // Distance that it needs to travel (negative = backwards)
    public static final double KD = 1.05; // KD Constant value
    public static final double MAX_SPEED = 0.3; // Maximum speed
    public static final double MAX_ROC = 0.01; // Maximum rate of change to make sure that the robot doesn't accelerate/decelerate too fast
    
    //Shooter and Intake
    public static final double SHOOTER_SPEED = 0.5; 
    public static final double SHOOTER_DISTANCE = 6; //rotations
    public static final double SHOOTER_TIME = 3;
    public static final double INTAKE_SPEED = 0.3;
    public static final double INTAKE_TIME = 10;
    
    //ports
    public static final int SHOOTER = 4;
    public static final int INTAKE = 5;
}


// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double AXIS_THRESHOLD = 0.1d;

    public static final class CANConfig {
        public static final int INTAKE_INDEXER_PORT = 1;
        public static final int SHOOTER_PORT = 2;
    }

    public static final class DrivetrainConfig {
        public static final double MAX_DRIVE_SPEED = 10.0d; // m/s
        public static final double MAX_TURN_SPEED = 10.0d; // rad/s
        public static final double SLOWMODE_FACTOR = 0.2d;
    }

    public static final class IndexerConfig {
        public static final double INDEXER_SPEED = 0.25d;
    }

    public static final class IntakeConfig {
        public static final double INTAKE_SPEED = 0.25d;
        public static final double OUTTAKE_SPEED = -0.25d;
    }

    public static final class ShooterConfig {
        public static final int MOTOR_ID = 193;
        public static final MotorType MOTOR_TYPE = MotorType.kBrushed;
        public static final PIDController MOTOR_CONTROLLER = new PIDController(1, 1, 1);
        public static final double SHOOT_WAIT_TIME = 3.0d;
    }

    public static void sendNumberToElastic(String name, double num, double places) {
        double newNum = Math.round(num * Math.pow(10, places)) / Math.pow(10, places);
        SmartDashboard.putNumber(name, newNum);
    }
}

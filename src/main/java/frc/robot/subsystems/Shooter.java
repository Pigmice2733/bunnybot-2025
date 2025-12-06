// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConfig;

public class Shooter extends SubsystemBase {
  SparkMax motor;
  // PIDController motorController = Constants.ShooterConfig.MOTOR_CONTROLLER;
  double targetSpeed = 0;

  /** Creates a new shooter. */
  public Shooter() {
    motor = new SparkMax(CANConfig.SHOOTER_PORT, MotorType.kBrushless);
  }

  /**
   * Sets the speed of the shooter motor
   *
   * @param spd Speed of the motor, between -1.0 and 1.0
   */
  public Command setShooterSpeed(double spd) {
    return Commands.runOnce(() -> targetSpeed = Math.max(-1, Math.min(1, spd)));
  }

  /**
   * @return Speed of the shooter motor
   */
  public double getShooterSpeed() {
    return motor.get();
  }

  /**
   * Sets the speed of the shooter motor to a specified speed
   * 
   * @param spd Speed of the motor, between -1.0 and 1.0
   */
  public Command startShooter(double spd) {
    return Commands.runOnce(() -> setShooterSpeed(spd));
  }

  /**
   * Sets the speed of the shooter motor to 0
   */
  public Command stopShooter() {
    return runOnce(() -> targetSpeed = 0);
  }

  /**
   * Checks whether the shooter is supposed to be stopped
   * 
   * @return Boolean value where true means the shooter is supposed to be stopeed
   */
  public boolean isTargetSpeedStop() {
    return targetSpeed == 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // motorController.calculate(motor.get(), targetSpeed);
    if (motor.get() != targetSpeed) {
      motor.set(targetSpeed);
    }
  }
}

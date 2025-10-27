// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Shoot;

public class ShooterSubsystem extends SubsystemBase {
  SparkMax motor = new SparkMax(Constants.ShooterConfig.MOTOR_ID, Constants.ShooterConfig.MOTOR_TYPE);
  // PIDController motorController = Constants.ShooterConfig.MOTOR_CONTROLLER;
  double targetSpeed = 0;

  /** Creates a new shooter. */
  public ShooterSubsystem() {
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command setMotor(double speed) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(() -> targetSpeed = speed);
  }

  public Command stopMotor() {
    return runOnce(() -> targetSpeed = 0);
  }

  public Command shoot(double speed) {
    return new Shoot(this, Constants.ShooterConfig.SHOOT_WAIT_TIME, speed);
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a
   * digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean isTargetSpeedStop() {
    // Query some boolean state, such as a digital sensor.
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

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

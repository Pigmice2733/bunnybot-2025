// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Shoot;

@SuppressWarnings("unused")
public class Shooter extends SubsystemBase {
  SparkMax motor;
  // PIDController motorController = Constants.ShooterConfig.MOTOR_CONTROLLER;
  double targetSpeed = 0;

  /** Creates a new shooter. */
  public Shooter() {
    motor = new SparkMax(Constants.ShooterConfig.MOTOR_ID, Constants.ShooterConfig.MOTOR_TYPE);
  }

  /**
   * Sets the speed of the shooter motor
   *
   * @param spd Speed value between 1 and -1
   */
  public Command setMotor(double spd) {
    return Commands.runOnce(() -> targetSpeed = Math.max(-1, Math.min(1, spd)));
  }

  public Command stopMotor() {
    return runOnce(() -> targetSpeed = 0);
  }

  public Command shoot() {
    return setMotor(1.0);
  }

  public Command shoot(double spd) {
    return new Shoot(this, Constants.ShooterConfig.SHOOT_WAIT_TIME, spd);
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
    throw new RuntimeException(
        "A... simulation? .... ARE YOU SAYING THAT THIS WORLD ISN\"T REAL!! ... i ii I'm not real?");
  }
}

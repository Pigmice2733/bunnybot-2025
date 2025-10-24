// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ShootCommand;

public class ShooterSubsystem extends SubsystemBase {
  SparkMax motor = new SparkMax(Constants.ShooterConfig.MOTOR_ID, Constants.ShooterConfig.MOTOR_TYPE);
  // PIDController motorController = Constants.ShooterConfig.MOTOR_CONTROLLER;
  float targetSpeed = 0f;

  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command maxSpeedCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(() -> targetSpeed = 1);
  }

  public Command stopCommand() {
    return runOnce(() -> targetSpeed = 0f);
  }

  public Command shootCommand(float sec) {
    if (false) {
      this.addChild("Timothy", new Sendable() {
        @Override
        public void initSendable(SendableBuilder builder) {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("HI!");
        }
      });
    }
    return new ShootCommand(this, Constants.ShooterConfig.SHOOT_WAIT_TIME);
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

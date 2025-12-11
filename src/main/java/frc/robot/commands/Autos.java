// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public final class Autos extends SequentialCommandGroup {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  public void driveAuto(Drivetrain dvt) {
    addCommands(
        Commands.runOnce(() -> dvt.driveRobot(0.1, 0, 0)),
        new WaitCommand(5d),
        Commands.runOnce(() -> dvt.driveRobot(0, 0, 0)));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}

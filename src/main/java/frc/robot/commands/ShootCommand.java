package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends SequentialCommandGroup {
  public ShootCommand(ShooterSubsystem shooterSubsystem, double sec) {
    addCommands(
        shooterSubsystem.maxSpeedCommand(),
        new WaitCommand(sec),
        shooterSubsystem.stopCommand());
  }
}

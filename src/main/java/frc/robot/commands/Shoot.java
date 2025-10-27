package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends SequentialCommandGroup {
  public Shoot(ShooterSubsystem shooterSubsystem, double sec, double speed) {
    addCommands(
        shooterSubsystem.setMotor(speed),
        new WaitCommand(sec),
        shooterSubsystem.stopMotor());
  }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;

public class Shoot extends SequentialCommandGroup {
  public Shoot(Shooter shoot, double sec, double spd) {
    addCommands(
        shoot.setShooterSpeed(spd),
        new WaitCommand(sec),
        shoot.stopShooter());
  }
}

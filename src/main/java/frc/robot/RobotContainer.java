// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.IndexerConfig;
import frc.robot.Constants.IntakeConfig;
import frc.robot.Constants.ShooterConfig;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

@SuppressWarnings("unused")
public class RobotContainer {
    private final Drivetrain drivetrain;

    private final Shooter shooter = new Shooter();
    private final Indexer indexer = new Indexer();
    private final Intake intake = new Intake();

    private final CommandXboxController driver;
    private final CommandXboxController operator;
    private final Controls controls;

    public RobotContainer() {
        driver = new CommandXboxController(0);
        operator = new CommandXboxController(1);
        controls = new Controls(driver, operator);

        drivetrain = new Drivetrain();
        configureBindings();
    }

    /**
     * Configure XBox controller bindings
     */
    private void configureBindings() {
        // OPERATOR
        operator.rightTrigger().onTrue(shooter.startShooter(ShooterConfig.SHOOTER_SPEED));
        operator.a().onTrue(indexer.startIndexer(IndexerConfig.INDEXER_SPEED));
        operator.rightBumper().onTrue(intake.startIntake(IntakeConfig.INTAKE_SPEED));
        operator.leftBumper().onTrue(intake.startIntake(IntakeConfig.OUTTAKE_SPEED));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}

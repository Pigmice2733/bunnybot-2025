// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.IntakeConfig;
import frc.robot.Constants.ShooterConfig;
import frc.robot.commands.DriveJoysticks;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

@SuppressWarnings("unused")
public class RobotContainer {
    private final Drivetrain drivetrain;

    // private final Shooter shooter = new Shooter();
    // private final Intake intake = new Intake();

    private final CommandXboxController driver;
    private final CommandXboxController operator;
    private final Controls controls;

    private boolean robotOriented;

    private SendableChooser<Command> autoChooser;

    public RobotContainer() {
        driver = new CommandXboxController(0);
        operator = new CommandXboxController(1);
        controls = new Controls(driver, operator);

        drivetrain = new Drivetrain();

        robotOriented = false;

        autoChooser = new SendableChooser<Command>();

        configureBindings();
        configureDefaultCommands();
        buildAutoChooser();
    }

    public void configureDefaultCommands() {
        drivetrain.setDefaultCommand(new DriveJoysticks(
                drivetrain,
                controls::getDriveSpeedX,
                controls::getDriveSpeedY,
                controls::getTurnSpeed,
                () -> robotOriented));
    }

    /**
     * Configure XBox controller bindings
     */
    private void configureBindings() {
        // OPERATOR
        // operator.rightTrigger().onTrue(shooter.startShooter(ShooterConfig.SHOOTER_SPEED));
        // operator.rightBumper().onTrue(intake.startIntake(IntakeConfig.INTAKE_SPEED));
        // operator.leftBumper().onTrue(intake.startIntake(IntakeConfig.OUTTAKE_SPEED));

        driver.a().onTrue(drivetrain.reset());
    }

    private void buildAutoChooser() {
        autoChooser.addOption("None", Commands.none());
        autoChooser.addOption("Drive Forward", Commands.runOnce(() -> drivetrain.simpleAuto(2)));
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}
